package com.capstone.lightalert.controller;

import com.capstone.lightalert.model.Users;
import com.capstone.lightalert.repository.UserRepository;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignUpController {
    UserRepository userRepository;

    public SignUpController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/signup")
    public String displayForm(){
        return "signup";
    }
    @PostMapping("/signup")
    public String crateAccount(Model model, @RequestParam String email, @RequestParam String password){
        // I chose 10MB memory or 1000 iteration only is because at the moment we care about our system to be fast
        Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder(16,32,1,10,1000);
        String hashedPassword = argon2PasswordEncoder.encode(password);
        Users user = new Users(email, hashedPassword);
        if(userRepository.findByEmail(email).isPresent()){
            model.addAttribute("error","This email already exists");
            return "signup";
        }else
            userRepository.save(user);

        return "redirect:/login";
    }
}
