package com.capstone.lightalert;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.capstone.lightalert.model.Users;
import com.capstone.lightalert.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private Argon2PasswordEncoder argon2PasswordEncoder;

    @BeforeEach
    void setUp() {
        Users mockUser = new Users("test@example.com", "hashedPassword");
        when(userRepository.findByEmail("test@example.com"))
                .thenReturn(Optional.of(mockUser));
        when(argon2PasswordEncoder.matches("correctPassword", "hashedPassword"))
                .thenReturn(true);
    }

    @Test
    void shouldReturnLoginView() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void loginSuccess() throws Exception {
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(16, 32, 1, 10, 1000);
        String hashedPassword = encoder.encode("password");
        Users user = new Users("user@example.com", hashedPassword);
        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));

        mockMvc.perform(post("/login")
                        .param("email", "user@example.com")
                        .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }

    @Test
    void shouldReturnErrorOnInvalidLogin() throws Exception {
        mockMvc.perform(post("/login")
                        .param("email", "wrong@example.com")
                        .param("password", "wrongPassword"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("error"));
    }
}

