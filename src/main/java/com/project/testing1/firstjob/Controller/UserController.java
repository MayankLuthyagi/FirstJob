package com.project.testing1.firstjob.Controller;

import com.project.testing1.firstjob.DTO.LoginDTO;
import com.project.testing1.firstjob.DTO.UserDTO;
import com.project.testing1.firstjob.Service.UserService;
import com.project.testing1.firstjob.response.LoginResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class UserController {

    private final UserService userService;

    public UserController(@Qualifier("userImpl") UserService userService) {
        this.userService = userService;
    }

    // Registration page (GET)
    @GetMapping("/register")
    public String registerPage() {
        return "join"; // Return the registration page
    }

    // Handle registration (POST)
    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDTO userDTO) {
        userService.add(userDTO);
        return "redirect:/signin"; // Redirect to signin after registration
    }

    // Login page (GET)
    @GetMapping("/signin")
    public String loginPage() {
        System.out.println("InGetMapping");
        return "login"; // Ensure this points to the login.html page
    }

    // Handle login (POST)
    @PostMapping("/signin")
    public String loginUser(@ModelAttribute LoginDTO loginDTO) {
        System.out.println(loginDTO.toString());
        LoginResponse loginResponse = userService.loginUser(loginDTO);
        if (loginResponse != null && loginResponse.getStatus()) {
            return "redirect:/home"; // Redirect to home after successful login
        } else {
            return "login"; // Stay on login page if login fails
        }
    }

    // Home page (GET) after login
    @GetMapping("/home")
    public String home() {
        return "home"; // Return the home.html page after login
    }
}
