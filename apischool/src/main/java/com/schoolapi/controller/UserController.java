package com.schoolapi.controller;

import com.schoolapi.model.User;
import com.schoolapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        // Log the incoming user details
        System.out.println("Received user: " + user);

        if (user.getAdmNo() == null || user.getAdmNo() <= 0) {
            throw new IllegalArgumentException("Admission number cannot be null or less than or equal to zero");
        }
        return userService.saveUser(user);
    }


    @PostMapping("/login")
    public User loginUser(@RequestBody User user) {
        User authenticatedUser = userService.authenticateUser(user.getUsername(), user.getPassword());
        if (authenticatedUser != null) {
            return authenticatedUser;
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
