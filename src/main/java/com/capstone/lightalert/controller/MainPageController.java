package com.capstone.lightalert.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    @GetMapping
    public String home(){
        return "mainPage";
    }
    @GetMapping("/contact")
    public String contactInfo(){
        return "contactInfo";
    }
    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
