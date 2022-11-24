package com.osuexam.microservice.controller;

import com.osuexam.microservice.Model.Login;
import com.osuexam.microservice.Repo.InstructorRepo;
import com.osuexam.microservice.Repo.LoginRepo;
import com.osuexam.microservice.Repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private LoginRepo loginRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private InstructorRepo instructorRepo;

    @PostMapping("/login")
    public Map<String, String> checkLogin(@RequestBody Login login)
    {
        Map<String, String> response = new HashMap<>();
        Login check = loginRepo.findById(login.getLoginId()).get();
        if (login.getPassword().equals(check.getPassword())){
            response.put("Message","Logged in successfully!");
        }else {
            response.put("Message","Failed to login.");
        }
        return response;
    }

    @PostMapping("/createUser")
    public Map<String, String> createUser(@RequestBody Login login)
    {
        Map<String, String> response = new HashMap<>();

        if(login.getStudent()!=null)
        {
            login.setStudent(studentRepo.saveAndFlush(login.getStudent()));
        }
        else
        {
            login.setInstructor(instructorRepo.saveAndFlush(login.getInstructor()));
        }
        loginRepo.saveAndFlush(login);
        response.put("Message","User created successfully");

        return response;
    }

}

