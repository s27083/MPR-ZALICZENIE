package com.s27083_bank.exceptions;

public class InvalidPeselException extends IllegalArgumentException {
    public InvalidPeselException(String pesel) {
        super("Invalid pesel: " + pesel);
    }
}
