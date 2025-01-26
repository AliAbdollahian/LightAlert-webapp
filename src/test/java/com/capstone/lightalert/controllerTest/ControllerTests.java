//package com.capstone.lightalert.controllerTest;
//
//
//import com.capstone.lightalert.model.Users;
//import com.capstone.lightalert.repository.UserRepository;
//import com.capstone.lightalert.repository.VideosRepository;
//import com.capstone.lightalert.service.BlobStorageService;
//import jakarta.servlet.http.Cookie;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
//import org.springframework.test.web.servlet.MockMvc;
//import java.util.Optional;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ControllerTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @MockBean
//    private VideosRepository videosRepository;
//
//    @Test
//    void uploadVideoRedirectsToLoginIfNoCookie() throws Exception {
//        mockMvc.perform(multipart("/upload-video")
//                        .file("videoFile", "data".getBytes()))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/login"));
//    }
//
//    @Test
//    void homeRedirectsToLoginIfNoCookie() throws Exception {
//        mockMvc.perform(get("/home"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/login"));
//    }
//
//    @Test
//    void loginFailsForInvalidEmail() throws Exception {
//        when(userRepository.findByEmail("invalid@example.com")).thenReturn(Optional.empty());
//
//        mockMvc.perform(post("/login")
//                        .param("email", "invalid@example.com")
//                        .param("password", "pass"))
//                .andExpect(status().isOk())
//                .andExpect(model().attribute("error", "Invalid email address."));
//    }
//
//    @Test
//    void loginSuccess() throws Exception {
//        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(16,32,1,10,1000);
//        String hashedPassword = encoder.encode("password");
//        Users user = new Users("user@example.com", hashedPassword);
//        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));
//
//        mockMvc.perform(post("/login")
//                        .param("email", "user@example.com")
//                        .param("password", "password"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/home"));
//    }
//
//    @Test
//    void signupFailsIfEmailExists() throws Exception {
//        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(new Users()));
//
//        mockMvc.perform(post("/signup")
//                        .param("email", "user@example.com")
//                        .param("password", "password"))
//                .andExpect(status().isOk())
//                .andExpect(model().attribute("error", "This email already exists"));
//    }
//
//    @Test
//    void signupSuccess() throws Exception {
//        when(userRepository.findByEmail("newuser@example.com")).thenReturn(Optional.empty());
//
//        mockMvc.perform(post("/signup")
//                        .param("email", "newuser@example.com")
//                        .param("password", "password"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/login"));
//    }
//}


