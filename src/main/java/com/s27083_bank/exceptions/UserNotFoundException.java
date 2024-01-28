package com.s27083_bank.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer id) {
        super("User with id " + id + " not found");
    }
}
