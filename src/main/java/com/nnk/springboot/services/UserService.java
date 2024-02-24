package com.nnk.springboot.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;

    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.repository = userRepository;
        this.encoder = encoder;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public User getUserById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    }

    public User update(Integer id, User user) {
        user.setId(id);
        return repository.save(user);
    }

    public void delete(User user) {
        repository.delete(user);
    }

}
