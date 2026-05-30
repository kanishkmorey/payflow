package com.payflow.payflow.service;

import com.payflow.payflow.entity.User;
import com.payflow.payflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    // Spring does dependency injection at startup, wiring the UserRepository bean into this service bean.
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByUpiId(String upiId) {
        return userRepository.findByUpiId(upiId);
    }

    public List<User> findUsersWithBalanceGreaterThan(Double amount) {
        return userRepository.findUsersWithBalanceGreaterThan(amount);
    }
}
