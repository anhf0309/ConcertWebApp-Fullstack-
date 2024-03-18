package com.anhfuentes.concertcapstone.controller;

import com.anhfuentes.concertcapstone.model.User;
import com.anhfuentes.concertcapstone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Other controller methods for user management
}
