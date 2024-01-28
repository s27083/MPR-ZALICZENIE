package com.s27083_bank.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.s27083_bank.model.User;
import com.s27083_bank.model.request.UserRequest;
import com.s27083_bank.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRequest entity) {
        User user = new User(null, entity.getName(), entity.getSurname(), entity.getPassword(), entity.getBalance(), entity.getCurrency(), entity.getPesel());
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok(registeredUser);
        
    }

    @GetMapping("/getById")
    public ResponseEntity<User> getById(@RequestParam Integer id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/getUsersWithBalanceGreaterThan")
    public ResponseEntity<List<User>> getUsersWithBalanceGreaterThan(@RequestParam Integer balance) throws IllegalArgumentException{
        try {
            return ResponseEntity.ok(userService.getUsersWithBalanceGreaterThan(balance));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    
    

}
