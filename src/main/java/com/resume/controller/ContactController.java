package com.resume.controller;

import com.resume.model.ContactForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller for the Contact page. Presents a contact form and performs
 * basic validation on user input. Since this demo application does not send
 * email, submitted messages are simply discarded after displaying a success
 * notification to the user.
 */
@Controller
public class ContactController {

    @GetMapping("/contact")
    public String showContactForm(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "contact";
    }

    @PostMapping("/contact")
    public String handleContactForm(@Valid @ModelAttribute("contactForm") ContactForm form,
                                    BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "contact";
        }
        // In a real application you'd send the message somewhere (e.g. email or database)
        model.addAttribute("successMessage", "Thank you for reaching out! I will get back to you soon.");
        // Clear the form for the next submission
        model.addAttribute("contactForm", new ContactForm());
        return "contact";
    }
}