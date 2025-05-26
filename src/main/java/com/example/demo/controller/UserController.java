package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@Validated
@RequestMapping("/api/users")
public interface UserController {

    @GetMapping
    ResponseEntity<?> getAllUsers();

    @GetMapping("/search")
    ResponseEntity<?> searchUsers(@RequestParam String query);

    @GetMapping("/{id}")
    ResponseEntity<?> getUserById(@PathVariable Long id);

    @GetMapping("/by-email")
    ResponseEntity<?> getUserByEmail(@RequestParam String email);
}
