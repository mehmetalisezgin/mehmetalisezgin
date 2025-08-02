package com.resume.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for the Tools page. Lists technologies and tools used.
 */
@Controller
public class ToolsController {
    @GetMapping("/tools")
    public String tools() {
        return "tools";
    }
}