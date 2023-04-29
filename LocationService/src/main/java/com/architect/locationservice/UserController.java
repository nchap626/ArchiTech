package com.architect.locationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) throws SQLException {
        User savedUser = userService.registerUser(user.getUsername(), user.getPassword(), user.getEmail());
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> loginUser(@RequestBody String username, @RequestBody String password) throws SQLException {
        User loggedInUser = userService.loginUser(username, password);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logoutUser(@RequestBody User user) {
        userService.logoutUser(user);
        return ResponseEntity.ok().build();
    }

}
