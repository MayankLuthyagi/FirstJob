package com.project.testing1.firstjob.Service;

import com.project.testing1.firstjob.DTO.LoginDTO;
import com.project.testing1.firstjob.DTO.UserDTO;
import com.project.testing1.firstjob.Model.User;
import com.project.testing1.firstjob.response.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String add(UserDTO userDTO);

    LoginResponse loginUser(LoginDTO loginDTO);
}
