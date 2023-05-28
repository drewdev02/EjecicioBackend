package com.demo.ejeciciobackend.controllers;

import com.demo.ejeciciobackend.models.User;
import com.demo.ejeciciobackend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("Fetching all users");
        var users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        log.info("Fetching user with ID: {}", id);
        var optionalUser = userRepository.findById(id);
        return optionalUser.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        log.info("Creating a new user: {}", user);
        var createdUser = userRepository.save(user);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        log.info("Updating user with ID: {}", id);
        var optionalUser = userRepository.findById(id);
        return optionalUser.map(existingUser -> {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            // Actualizar otros campos según sea necesario
            User updatedUser = userRepository.save(existingUser);
            return ResponseEntity.ok(updatedUser);
        }).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        log.info("Deleting user with ID: {}", id);
        var optionalUser = userRepository.findById(id);
        optionalUser.ifPresent(user -> {
            userRepository.deleteById(id);
        });
        return optionalUser.map(user -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }


}
