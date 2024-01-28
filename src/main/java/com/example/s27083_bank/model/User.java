package com.example.s27083_bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private Integer id;
    private String name;
    private String surname;
    private String password;
    private Integer balance;
    private Currency currency;
    private Integer pesel;

}
