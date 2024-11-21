package com.project.testing1.firstjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = { org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class })
public class FirstJobApplication {
    public static void main(String[] args) {
        SpringApplication.run(FirstJobApplication.class, args);
    }
}
