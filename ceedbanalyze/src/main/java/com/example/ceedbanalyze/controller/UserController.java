package com.example.ceedbanalyze.controller;

/*
 * @author      潘广成
 * @data        2024/8/12 17:58
 * @version     1.0
 * @description
 */

import com.example.ceedbanalyze.Service.UserService;
import com.example.ceedbanalyze.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public User Login(String username, String password) {
        User user = userService.getByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }
}
