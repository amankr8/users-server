package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {

    void loadUsers();

    List<User> getAllUsers();

    List<User> searchUsers(String query);

    User getUserById(Long id);

    User getUserByEmail(String email);
}
