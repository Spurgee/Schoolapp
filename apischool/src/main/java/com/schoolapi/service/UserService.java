package com.schoolapi.service;

import com.schoolapi.model.User;
import com.schoolapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }



    public User authenticateUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
