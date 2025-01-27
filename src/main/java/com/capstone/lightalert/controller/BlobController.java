package com.capstone.lightalert.controller;

import com.capstone.lightalert.model.Users;
import com.capstone.lightalert.repository.UserRepository;
import com.capstone.lightalert.service.BlobStorageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class BlobController {

    private BlobStorageService blobStorageService;
    private UserRepository userRepository;


    public BlobController(BlobStorageService blobStorageService, UserRepository userRepository) {
        this.blobStorageService = blobStorageService;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/upload-video")
    public String displayHomeAfterUploading() {
        return "redirect:/home";
    }

    @PostMapping("/upload-video")
    public String uploadVideo(@RequestParam("videoFile") MultipartFile videoFile,
                              @CookieValue(value = "user", defaultValue = "") String email,
                              RedirectAttributes redirectAttributes) {
        if (email.isEmpty()) {
            return "redirect:/login";
        }

        try {

            Users user = userRepository.findByEmail(email).orElse(null);

            assert user != null;
            System.out.println(user.getSystemId()+"SYSTEM ID:"+user.getSystemId());
            String videoUrl = blobStorageService.uploadVideo(videoFile, user.getSystemId());

            redirectAttributes.addFlashAttribute("message", "Video uploaded successfully: " + videoUrl);
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Failed to upload video: " + e.getMessage());
        }

        return "redirect:/home";
    }

    @ControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(MaxUploadSizeExceededException.class)
        public String handleMaxSizeException(RedirectAttributes redirectAttributes) {
            redirectAttributes.addFlashAttribute("error", "File size exceeds the maximum limit!");
            return "redirect:/home";
        }
    }

}

