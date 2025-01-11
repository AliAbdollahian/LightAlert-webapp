package com.capstone.lightalert.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
/**
 * Health check with an alert.
 */
public class HealthController {

    @GetMapping("/alert")
    public String healthCheck() {
        return "alert";
    }
}
