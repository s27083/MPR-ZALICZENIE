package com.s27083_bank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.s27083_bank.exceptions.InvalidBalanceException;
import com.s27083_bank.exceptions.InvalidPeselException;
import com.s27083_bank.model.Currency;
import com.s27083_bank.model.User;
import com.s27083_bank.repository.UserRepository;
import com.s27083_bank.service.UserService;

import io.swagger.v3.oas.annotations.Parameter;

public class UserServiceTest {

    private static UserService userService;
    private static UserRepository userRepository;

     @BeforeAll
    static void setup() {
        userRepository = new UserRepository();
        userService = new UserService(userRepository);
    }

    @AfterEach
    void clear() {
        userRepository.removeAll();
    }

    @Test
    void shouldRegisterUser() {
        // given
        User user = new User(null, "Jan", "Kowalski", "1234", 1000, Currency.PLN , "11111111111");
        // when
        User registeredUser = userService.registerUser(user);
        // then
        assertNotNull(registeredUser.getId());
        assertEquals(registeredUser.getName(), user.getName());
        assertEquals(registeredUser.getSurname(), user.getSurname());
        assertEquals(registeredUser.getBalance(), user.getBalance());
        assertEquals(registeredUser.getCurrency(), user.getCurrency());
        assertEquals(registeredUser.getPesel(), user.getPesel());

    }

    @Test
    void shouldNotRegisterUserDueToInvalidBalance() {
        // given
        User user = new User(null, "Jan", "Kowalski", "1234", -1000, Currency.PLN , "11111111111");
        // when
        InvalidBalanceException result = assertThrows(InvalidBalanceException.class, () -> userService.registerUser(user));

        // then
       assertEquals("Balance cannot be negative", result.getMessage());
    }

    @Test
    void shouldNotRegisterUserDueToInvalidPesel() {
        // given
        User user = new User(null, "Jan", "Kowalski", "1234", 1000, Currency.PLN , "111111111111");
        // when
        InvalidPeselException result = assertThrows(InvalidPeselException.class, () -> userService.registerUser(user));

        // then
       assertEquals("Invalid pesel: "+user.getPesel(), result.getMessage());
    }

    @Test
    void shouldGetUserById() {
        // given
        User user = new User(null, "Jan", "Kowalski", "1234", 1000, Currency.PLN , "11111111111");
        User registeredUser = userService.registerUser(user);
        // when
        User result = userService.getById(registeredUser.getId());
        // then
        assertEquals(registeredUser.getId(), result.getId());
        assertEquals(registeredUser.getName(), result.getName());
        assertEquals(registeredUser.getSurname(), result.getSurname());
        assertEquals(registeredUser.getBalance(), result.getBalance());
        assertEquals(registeredUser.getCurrency(), result.getCurrency());
        assertEquals(registeredUser.getPesel(), result.getPesel());
    }

    @ParameterizedTest
    @ValueSource(ints = { 1000, 2000, 3000 })
    void shouldGetUsersWithBalance(int balance) {
        // given
        User user = new User(null, "Jan", "Kowalski", "1234", balance, Currency.PLN , "11111111411");
        User user2 = new User(null, "Jan2", "Kowalski2", "1234", balance, Currency.PLN , "11141111112");
        User user3= new User(null, "Jan2", "Kowalski2", "1234", 20, Currency.PLN , "11111111112");

        User registeredUser = userService.registerUser(user);
        User registeredUser2 = userService.registerUser(user2);
        User registeredUser3 = userService.registerUser(user3);
        // when
        
        
        // then
        assertEquals(2, userService.getUsersWithBalanceGreaterThan(500).size());
    }
    

    

}
