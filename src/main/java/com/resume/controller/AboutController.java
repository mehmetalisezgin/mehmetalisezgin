package com.resume.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for the About page. Returns a Thymeleaf template containing
 * an extended biography and background information.
 */
@Controller
public class AboutController {
    @GetMapping("/about")
    public String about() {
        return "about";
    }
}