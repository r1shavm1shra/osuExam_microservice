package com.example.ecommerce.controller;

import com.example.ecommerce.Model.Login;
import com.example.ecommerce.Repo.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private LoginRepo loginRepo;

    @PostMapping("/login")
    public Map<String, String> checkLogin(@RequestBody Login login)
    {
        Map<String, String> response = new HashMap<>();
        Optional<Login> dbLogin = loginRepo.findById(login.getLoginId());
        if (!dbLogin.isPresent()){
            loginRepo.save(login);
            response.put("Message","Successfully created a new account!");
        }else {
            Login check = loginRepo.findById(login.getLoginId()).get();
            if (login.getPassword().equals(check.getPassword())){
                response.put("Message","Logged in successfully!");
            }else {
                response.put("Message","Failed to login.");
            }
        }
        return response;
    }
}

