package com.s27083_bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.s27083_bank.exceptions.InvalidBalanceException;
import com.s27083_bank.exceptions.InvalidPeselException;
import com.s27083_bank.exceptions.UserNotFoundException;
import com.s27083_bank.model.User;
import com.s27083_bank.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User registerUser(User user) {
        if (user.getBalance() < 0) {
            throw new InvalidBalanceException();
        }
        if (!isValidPesel(user.getPesel())) {
            throw new InvalidPeselException(user.getPesel());
        }
        if (userRepository.getAll().stream().anyMatch(u -> u.getPesel().equals(user.getPesel()))) {
            throw new InvalidPeselException(user.getPesel());
        }
        return userRepository.registerUser(user);
    }

   
    public User getById(Integer id) {
        Optional<User> user = userRepository.getById(id);
        return user.orElseThrow(() -> new UserNotFoundException(id));
    }
    public List<User> getUsersWithBalanceGreaterThan(Integer balance) {
        if (balance < 0) {
            throw new InvalidBalanceException();
        }
        return userRepository.getUsersWithBalanceGreaterThan(balance);
    }

    public boolean isValidPesel(String pesel) {
        if (pesel == null || pesel.length() != 11) {
            return false;
        }
        for (char c : pesel.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

}
