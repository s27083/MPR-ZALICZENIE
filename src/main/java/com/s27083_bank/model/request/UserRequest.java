package com.s27083_bank.model.request;

import com.s27083_bank.model.Currency;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class UserRequest {
    private String name;
    private String surname;
    private String password;
    private Integer balance;
    private Currency currency;
    private String pesel;


    
}
