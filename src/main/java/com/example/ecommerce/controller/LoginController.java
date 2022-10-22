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

    private List<Login> getAll()
    {
        return loginRepo.findAll();
    }

    @PostMapping("/login")
    public Map<String, String> checkLogin(@RequestBody Login login)
    {
        List<Login> loginDetails = getAll();

        Map<String, String> response = new HashMap<>();
        if (!loginDetails.contains(login)){
            loginRepo.save(login);
            response.put("Message","Successfully created a new account!");
        }else {
            int index = loginDetails.indexOf(login);
            Login check = loginDetails.get(index);
            if (login.getPassword().equals(check.getPassword())){
                response.put("Message","Logged in successfully!");
            }else {
                response.put("Message","Failed to login.");
            }
        }
        return response;
    }
}

