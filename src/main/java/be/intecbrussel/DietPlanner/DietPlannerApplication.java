package be.intecbrussel.DietPlanner;

import be.intecbrussel.DietPlanner.model.Address;
import be.intecbrussel.DietPlanner.model.ProductMeal;
import be.intecbrussel.DietPlanner.model.Role;
import be.intecbrussel.DietPlanner.model.UserEntity;
import be.intecbrussel.DietPlanner.model.request.ProductRequest;
import be.intecbrussel.DietPlanner.model.request.UserRequest;
import be.intecbrussel.DietPlanner.repository.RoleRepository;
import be.intecbrussel.DietPlanner.repository.UserRepository;
import be.intecbrussel.DietPlanner.service.database.MealDataBaseService;
import be.intecbrussel.DietPlanner.service.products.ProductService;
import be.intecbrussel.DietPlanner.service.user.UserService;
import be.intecbrussel.DietPlanner.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
/*import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

 */

@SpringBootApplication
@RequiredArgsConstructor
public class DietPlannerApplication {



    public static void main(String[] args) {
        SpringApplication.run(DietPlannerApplication.class, args);
    }

/*
    @Bean
    CommandLineRunner run(UserService userService, MealDataBaseService mealDataBaseService){
        return args -> {
            mealDataBaseService.createDatabase();

            userService.saveRole(new Role("ROLE_MANAGER"));
            userService.saveRole(new Role( "ROLE_SUPER_ADMIN"));
            userService.saveRole(new Role("ROLE_ADMIN"));
            userService.saveRole(new Role("ROLE_USER"));

            userService.saveUser(new UserRequest("Jim Schyvens", "Patron", "password", "patron@gmail.com"));
            userService.saveUser(new UserRequest("axel de beast", "axel", "password", "axel@gmail.com"));
            userService.saveUser(new UserRequest("toufik", "toufik", "password", "toufik@gmail.com"));
            userService.saveUser(new UserRequest("yilmaz", "yilmaz", "password", "yilmaz@gmail.com"));

            userService.addRoleToUser("Patron", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("axel", "ROLE_ADMIN");
            userService.addRoleToUser("Patron", "ROLE_MANAGER");
        };

 */

/*
        //mealDataBaseService.createDatabase();

        productService.saveProductMeal("chicken");
        productService.saveProductMeal("rice");


        ProductRequest request1 = new ProductRequest("rice", 130, 28, 0.3, 2.7, 0.1, 100, 1 );

		ProductRequest request = new ProductRequest("chicken", 239, 0, 14, 27,0, 100, 1);

		productService.saveProduct(request1);


		//Cleanup the users table
        userRepository.deleteAllInBatch();
		// Insert a new user in the database



		Address address = new Address("200", "mijn Straat", "Europe",
				"Karnataka", "India", "560008");

		Collection<Role> roles = new ArrayList<>();
		Role admin = new Role();
		admin.setName("ADMIN");
		roles.add(admin);
		UserEntity user = new UserEntity("Patron", "Patron", "patronv@com.com", passwordEncoder().encode("P"), address, roles );
		userRepository.save(user);
}
        */


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
