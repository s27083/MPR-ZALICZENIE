package com.s27083_bank.exceptions;

public class InvalidBalanceException extends RuntimeException {

    public InvalidBalanceException() {
        super("Balance cannot be negative");
    }
}
