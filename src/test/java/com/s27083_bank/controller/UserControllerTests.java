package com.s27083_bank.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.s27083_bank.model.Currency;
import com.s27083_bank.model.User;
import com.s27083_bank.model.request.UserRequest;
import com.s27083_bank.repository.UserRepository;



@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void cleanUp() {
        userRepository.removeAll();
    }

    @Test
    void shouldRegisterNewUser() throws Exception {
        UserRequest user = new UserRequest("Jan", "Kowalski", "1234", 1000, Currency.PLN , "11111111111");
        String userJson = objectMapper.writeValueAsString(user);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        User registeredUser = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), User.class);
        assertNotEquals(userJson, registeredUser);
        assertEquals(user.getPesel(), registeredUser.getPesel());

        Optional<User> userFromRepository = userRepository.getById(registeredUser.getId());
        assertEquals(registeredUser, userFromRepository.get());
    }

    @Test
    void shouldGetUserById() throws Exception {
        User user = new User(null, "Jan", "Kowalski", "1234", 1000, Currency.PLN , "11111111111");
        User registeredUser = userRepository.registerUser(user);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/getById")
                .param("id", registeredUser.getId().toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        User userFromResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), User.class);
        assertEquals(registeredUser, userFromResponse);
    }

}
