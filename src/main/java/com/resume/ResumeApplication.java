package com.resume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Spring Boot application. Running the main method starts
 * an embedded Tomcat server on the default port (8080) and serves the
 * application. Spring Boot will automatically scan the com.resume package and
 * its subpackages for annotated components such as controllers and services.
 */
@SpringBootApplication
public class ResumeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResumeApplication.class, args);
    }
}