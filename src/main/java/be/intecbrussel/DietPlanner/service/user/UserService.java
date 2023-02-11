package be.intecbrussel.DietPlanner.service.user;

import be.intecbrussel.DietPlanner.model.Address;
import be.intecbrussel.DietPlanner.model.Role;
import be.intecbrussel.DietPlanner.model.UserEntity;
import be.intecbrussel.DietPlanner.model.request.LoginRequest;
import be.intecbrussel.DietPlanner.model.request.UserRequest;

import java.util.List;

public interface UserService {

    UserEntity saveUser(UserRequest user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    UserEntity getUserByUserName(String username);

    void addAddressToUser(String username, Address address);

    List<UserEntity> getAllUsers();

    boolean login(LoginRequest request);

    void deleteUserById(Long id);
}
