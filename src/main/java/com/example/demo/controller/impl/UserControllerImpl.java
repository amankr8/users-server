package com.example.demo.controller.impl;

import com.example.demo.controller.UserController;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @Override
    public ResponseEntity<?> searchUsers(String query) {
        List<User> users = userService.searchUsers(query);
        return ResponseEntity.ok().body(users);
    }

    @Override
    public ResponseEntity<?> getUserById(Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @Override
    public ResponseEntity<?> getUserByEmail(String email) {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok().body(user);
    }
}
