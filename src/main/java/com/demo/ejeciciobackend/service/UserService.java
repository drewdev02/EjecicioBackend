package com.demo.ejeciciobackend.service;

import com.demo.ejeciciobackend.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<List<User>> getAllUsers();

    Optional<User> getUserById(Long id);

    Optional<User> createUser(User user);

    Optional<User> updateUser(Long id, User user);

    void deleteUser(Long id);

    Optional<User> findOneByEmail(String email);
}
