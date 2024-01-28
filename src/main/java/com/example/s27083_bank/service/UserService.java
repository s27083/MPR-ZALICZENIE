package com.example.s27083_bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.s27083_bank.model.User;
import com.example.s27083_bank.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User registerUser(User user) {
        if (user.getBalance() < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        return userRepository.registerUser(user);
    }
    public Optional<User> getById(Integer id) {
        return userRepository.getById(id);
    }
    public List<User> getUsersWithBalanceGreaterThan(Integer balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        return userRepository.getUsersWithBalanceGreaterThan(balance);
    }

}
