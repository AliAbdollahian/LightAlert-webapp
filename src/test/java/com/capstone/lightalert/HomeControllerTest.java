package com.capstone.lightalert;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.capstone.lightalert.model.Users;
import com.capstone.lightalert.repository.UserRepository;
import com.capstone.lightalert.repository.VideosRepository;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private VideosRepository videosRepository;

    @BeforeEach
    void setUp() {
        Users mockUser = new Users("test@example.com", "hashedPassword");
        mockUser.setSystemId("1L");
        Mockito.when(userRepository.findByEmail("test@example.com"))
                .thenReturn(Optional.of(mockUser));

        Mockito.when(videosRepository.findByUser_SystemId("1L"))
                .thenReturn(List.of(/* Populate mock Videos */));
    }

    @Test
    void shouldRedirectToLoginIfNoCookie() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    void shouldDisplayHomePageForValidUser() throws Exception {
        mockMvc.perform(get("/home").cookie(new Cookie("user", "test@example.com")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("videos"));
    }
}

