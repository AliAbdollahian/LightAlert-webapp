package com.capstone.lightalert.controller;

import com.capstone.lightalert.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final UserRepository userRepository;
    private final Argon2PasswordEncoder argon2PasswordEncoder;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.argon2PasswordEncoder = new Argon2PasswordEncoder(16, 32, 1, 10, 1000);
    }

    @GetMapping("/login")
    public String displayLogin() {
        return "login";
    }
    @PostMapping("/login")
    public String processLogin(@RequestParam String email, @RequestParam String password , Model model, HttpServletResponse response) {
        var user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            model.addAttribute("error", "Invalid email address.");
            return "login";
        }

        if (!argon2PasswordEncoder.matches(password, user.get().getPassword())) {
            model.addAttribute("error", "Invalid password.");
            return "login";
        }

        Cookie userCookie = new Cookie("user", email);
        userCookie.setPath("/");
        response.addCookie(userCookie);

        return "redirect:/home";
    }
}
