package com.capstone.lightalert;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
/**
 * Health check with bananas.
 */
public class HealthController {

    @GetMapping("/alert")
    public String healthCheck() {
        return "alert";
    }
}
