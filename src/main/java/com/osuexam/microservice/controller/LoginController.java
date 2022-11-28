package com.osuexam.microservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.osuexam.microservice.Model.Course;
import com.osuexam.microservice.Model.Exam;
import com.osuexam.microservice.Model.Login;
import com.osuexam.microservice.Repo.InstructorRepo;
import com.osuexam.microservice.Repo.LoginRepo;
import com.osuexam.microservice.Repo.StudentRepo;
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

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private InstructorRepo instructorRepo;

    @PostMapping("/login")
    public Map<String, String> checkLogin(@RequestBody Login login) throws JsonProcessingException {
        Map<String, String> response = new HashMap<>();
        Login check = loginRepo.findById(login.getLoginId()).get();
        if (login.getPassword().equals(check.getPassword())){
            response.put("Message","Logged in successfully!");
            response.put("loginType",check.getLoginType()+"");
            if(check.getLoginType()=='S')
            {response.put("firstName",check.getStudent().getFirstName());
            response.put("lastName",check.getStudent().getLastName());
            List<Course> courseList = check.getStudent().getCourseList();
            for(Course course: courseList)
            {
                Exam exam = course.getExam();
                exam.setAnswerKey(null);
                exam.getRooms().setSeats(exam.getRooms().getSeats().stream().filter(x->x.getStudent().getStudentId() == check.getStudent().getStudentId()).toList());
            }
            response.put("courseList",new ObjectMapper().writeValueAsString(check.getStudent().getCourseList()));
            }
            else {
                {response.put("firstName",check.getInstructor().getFirstName());
                    response.put("lastName",check.getInstructor().getLastName());}
            }
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

