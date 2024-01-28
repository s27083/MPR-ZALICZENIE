package com.s27083_bank.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.s27083_bank.model.User;


@Repository
public class UserRepository {
    List<User> users = new ArrayList<User>();

    public User registerUser(User user) {
        user.setId(users.size() + 1);
        users.add(user);
        return user;
    }

    public Optional<User> getById(Integer id) {
        return users.stream()
                .filter(car -> car.getId().equals(id))
                .findFirst();
        
    }

    public List<User> getUsersWithBalanceGreaterThan(Integer balance) {
        return users.stream()
                .filter(car -> car.getBalance() > balance)
                .collect(Collectors.toList());
    } 

    public List<User> getAll() {
        return users;
    }

    public void deleteById(Integer id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    public void removeAll() {
        users.clear();
    }   

}
