package com.resume.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for the Experience page. Displays work history and roles.
 */
@Controller
public class ExperienceController {
    @GetMapping("/experience")
    public String experience() {
        return "experience";
    }
}