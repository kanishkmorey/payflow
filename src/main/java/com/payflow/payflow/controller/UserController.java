package com.payflow.payflow.controller;

import com.payflow.payflow.entity.User;
import com.payflow.payflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/upi/{upiId}")
    public User findByUpiId(@PathVariable String upiId) {
        return userService.findByUpiId(upiId);
    }

    @GetMapping("/rich")
    public List<User> getRichUsers(@RequestParam("minBalance") Double minBalance) {
        return userService.findUsersWithBalanceGreaterThan(minBalance);
    }
}
