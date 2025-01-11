package com.capstone.lightalert.controller;

import com.capstone.lightalert.model.Users;
import com.capstone.lightalert.repository.UserRepository;
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
        Users user = new Users(email, password);
        if(userRepository.findByEmail(email).isPresent()){
            model.addAttribute("error","This email already exists");
            return "signup";
        }else
            userRepository.save(user);

        return "redirect:/login";
    }
}
