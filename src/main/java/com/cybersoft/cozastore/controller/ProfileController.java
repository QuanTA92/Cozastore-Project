package com.cybersoft.cozastore.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String showProfile(@AuthenticationPrincipal User user) {
        if (user != null) {
            return "profile";
        } else {
            return "redirect:/login";
        }
    }
}
