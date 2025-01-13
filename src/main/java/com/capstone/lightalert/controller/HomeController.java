package com.capstone.lightalert.controller;

import com.capstone.lightalert.model.Videos;
import com.capstone.lightalert.repository.UserRepository;
import com.capstone.lightalert.repository.VideosRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    UserRepository userRepository;
    VideosRepository videosRepository;
    public HomeController(UserRepository userRepository, VideosRepository videosRepository) {
        this.userRepository = userRepository;
        this.videosRepository = videosRepository;
    }

    @GetMapping("/home")
    public String DisplayHome(Model model , @CookieValue(value = "user", defaultValue = "") String email) {
        if (email.isEmpty()) {
            return "redirect:/login";
        }
        userRepository.findByEmail(email).ifPresent(user -> {
            model.addAttribute("user", user);
            List<Videos> videosList = videosRepository.findByUser_SystemId(user.getSystemId());
            model.addAttribute("videos", videosList);
        });
        return "home";
    }
}
