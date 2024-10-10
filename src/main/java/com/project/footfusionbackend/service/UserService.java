package com.project.footfusionbackend.service;

import com.project.footfusionbackend.model.User;
import com.project.footfusionbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User getUserById(Long id) {
        return userRepository.findByUserId(id);
    }

    public Optional<User> getUserByEmailId(String emailId) {
        return userRepository.findByEmailId(emailId);
    }

    public Optional<User> getUserByContactNo (String contactNo) {
        return userRepository.findByContactNo(contactNo);
    }
}
