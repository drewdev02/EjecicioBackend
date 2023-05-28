package com.demo.ejeciciobackend.service.impl;

import com.demo.ejeciciobackend.models.User;
import com.demo.ejeciciobackend.repository.UserRepository;
import com.demo.ejeciciobackend.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<List<User>> getAllUsers() {
        var users = userRepository.findAll();
        return Optional.ofNullable(users);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> createUser(User user) {
        var createdUser = userRepository.save(user);
        return Optional.ofNullable(createdUser);
    }

    @Override
    public Optional<User> updateUser(Long id, User user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    User updatedUser = User.builder()
                            .id(existingUser.getId())
                            .name(user.getName())
                            .email(user.getEmail())
                            .role(user.getRole())
                            .build();
                    updatedUser = userRepository.save(updatedUser);
                    return Optional.ofNullable(updatedUser);
                })
                .orElse(Optional.empty());
    }


    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findOneByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }
}

