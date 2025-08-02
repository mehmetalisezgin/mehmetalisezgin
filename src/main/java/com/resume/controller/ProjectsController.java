package com.resume.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for the Projects page. Lists personal and collaborative projects.
 */
@Controller
public class ProjectsController {
    @GetMapping("/projects")
    public String projects() {
        return "projects";
    }
}