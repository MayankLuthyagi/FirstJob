package com.project.testing1.firstjob.Service.Impl;

import com.project.testing1.firstjob.DTO.LoginDTO;
import com.project.testing1.firstjob.DTO.UserDTO;
import com.project.testing1.firstjob.Model.User;
import com.project.testing1.firstjob.Service.UserService;
import com.project.testing1.firstjob.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.testing1.firstjob.Repo.UserRepo;

import java.util.Optional;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String add(UserDTO userDTO) {
        // Create a User object from the DTO
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encode the password before saving
        // Log the user before saving
        System.out.println("Saving user: " + user);
        // Save the user using the repository
        userRepo.save(user);  // This will persist the user in the database
        // Log after saving
        System.out.println("User saved: " + user);
        return "User saved: " + user.toString(); // Return the saved user info
    }

    @Override
    public LoginResponse loginUser(LoginDTO loginDTO) {
        String msg ="";
        User user1 = userRepo.findByEmail(loginDTO.getEmail());
        System.out.println(user1);
        if(user1 != null) {
            if(passwordEncoder.matches(loginDTO.getPassword(), user1.getPassword())) {
                Optional<User> user = userRepo.findOneByEmailAndPassword(loginDTO.getEmail(), user1.getPassword());
                if(user.isPresent()) {
                    return new LoginResponse("Login Success", true);
                }else{
                    return new LoginResponse("Login Failed", false);
                }
            }
            else{
                return new LoginResponse("Password Failed", false);
            }
        }
        else{
            return new LoginResponse("Email Not Found", false);
        }

    }
}
