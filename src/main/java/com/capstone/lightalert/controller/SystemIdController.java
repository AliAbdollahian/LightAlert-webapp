package com.capstone.lightalert.controller;


import com.capstone.lightalert.model.Users;
import com.capstone.lightalert.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SystemIdController {
    UserRepository userRepository;
    public SystemIdController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/home/systemId")
    public String displayGetSystemId(Model model, @CookieValue(value = "user", defaultValue = "") String email) {
        if (email.isEmpty()) {
            return "redirect:/login";
        }

        Users user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return "redirect:/login";
        }

        if (user.getSystemId() != null && !user.getSystemId().isEmpty()) {
            model.addAttribute("message", "System ID already entered.");
        }

        return "getSystemId";
    }

    @PostMapping("/home/systemId")
    public String getSystemId(@RequestParam("systemId") String systemId,
                              @CookieValue(value = "user", defaultValue = "") String email,
                              RedirectAttributes redirectAttributes) {

        if (email.isEmpty()) {
            return "redirect:/login";
        }

        Users user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return "redirect:/login";
        }

        if (user.getSystemId() == null || user.getSystemId().isEmpty()) {
            user.setSystemId(systemId);
            userRepository.save(user);
            redirectAttributes.addFlashAttribute("message", "System ID updated successfully!");
        } else if (!user.getSystemId().equals(systemId)) {
            // If a system ID is already set and it's different from the new one, update it
            user.setSystemId(systemId);
            userRepository.save(user);
            redirectAttributes.addFlashAttribute("message", "System ID updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("message", "System ID is already the same.");
        }

        return "redirect:/home";
    }
}
