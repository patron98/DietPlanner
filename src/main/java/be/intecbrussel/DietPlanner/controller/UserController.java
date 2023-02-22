package be.intecbrussel.DietPlanner.controller;

import be.intecbrussel.DietPlanner.model.Role;
import be.intecbrussel.DietPlanner.model.UserEntity;
import be.intecbrussel.DietPlanner.model.request.UserRequest;
import be.intecbrussel.DietPlanner.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<UserEntity> createUser(@Valid @RequestBody UserRequest request){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(request));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> createUser(@Valid @RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/user/{username}/roles")
    public ResponseEntity<Role> addRoleToUserName(@Valid @RequestBody String roleName, @PathVariable String username){
        userService.addRoleToUser(username, roleName);
        return ResponseEntity.ok().build();
    }





}
