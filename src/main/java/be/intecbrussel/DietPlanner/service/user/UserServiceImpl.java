package be.intecbrussel.DietPlanner.service.user;

import be.intecbrussel.DietPlanner.model.Address;
import be.intecbrussel.DietPlanner.model.MealsDataBase;
import be.intecbrussel.DietPlanner.model.Role;
import be.intecbrussel.DietPlanner.model.UserEntity;
import be.intecbrussel.DietPlanner.model.request.LoginRequest;
import be.intecbrussel.DietPlanner.model.request.UserRequest;
import be.intecbrussel.DietPlanner.repository.MealDatabaseRepository;
import be.intecbrussel.DietPlanner.repository.RoleRepository;
import be.intecbrussel.DietPlanner.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final MealDatabaseRepository mealDatabaseRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("no username found"));


        log.info("User found in database: {}", username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(user.getUsername(), user.getPassword(), authorities);

    }

    public boolean login(LoginRequest request){

        return userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword()).isPresent();

    }

    @Override
    public UserEntity saveUser(UserRequest request) {
        boolean checkUsername = userRepository.findByUsername(request.getUsername()).isPresent();
        boolean checkEmail = userRepository.findByEmail(request.getEmail()).isPresent();
        if (checkUsername|| checkEmail) {
            throw new IllegalArgumentException("username or email already in use");
        }
        log.info("Saving new user {} to database", request.getName());
        MealsDataBase dataBase = mealDatabaseRepository.findById(1L).orElseThrow(() -> new UsernameNotFoundException("No database found"));
        UserEntity user = new UserEntity();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setMealsDataBaseId(dataBase.getId());
        user.getRoles().add(roleRepository.findByName("ROLE_USER"));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to database", role.getName());
        return roleRepository.save(role);
    }


    @Override
    public void addAddressToUser(String username, Address address) {
        log.info("adding Address to user: {}", username);
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        user.setAddress(address);
        userRepository.save(user);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding new role {} to user {}", roleName, username);
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("no user found"));
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);

    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity getUserByUserName(String username) {
        log.info("Fetching user:{}", username);
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No user found"));


    }

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(Long id) {
        log.info("Deleting user with id: {}", id);
        userRepository.deleteById(id);
    }
}