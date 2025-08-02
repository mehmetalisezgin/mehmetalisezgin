package com.resume.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data transfer object for the contact form on the Contact page. Fields are
 * annotated with validation constraints to ensure that the name, email and
 * message are provided and the email conforms to a valid format. Spring
 * automatically binds form fields to this bean.
 */
public class ContactForm {
    @NotBlank(message = "Please enter your name.")
    @Size(max = 60, message = "Name must not exceed 60 characters.")
    private String name;

    @NotBlank(message = "Please enter your email address.")
    @Email(message = "Please enter a valid email address.")
    private String email;

    @NotBlank(message = "Please enter a message.")
    @Size(max = 1000, message = "Message must not exceed 1000 characters.")
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}