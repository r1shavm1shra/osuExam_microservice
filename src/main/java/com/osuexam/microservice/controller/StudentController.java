package com.osuexam.microservice.controller;

import com.osuexam.microservice.Model.Exam;
import com.osuexam.microservice.Model.Login;
import com.osuexam.microservice.Model.Student;
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
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @PostMapping("/createStudentAndAssignCourses")
    public Map<String, String> createStudent(@RequestBody Student student)
    {
        Map<String, String> response = new HashMap<>();
        studentRepo.save(student);
        response.put("Message","Saved student data");
        return response;
    }

    @GetMapping("/getAllStudents")
    public List<Student> getStudent()
    {
        return studentRepo.findAll();
    }

    @GetMapping("/student/id")
    public Optional<Student> getStudent(@PathVariable String id)
    {
        return studentRepo.findById(id);
    }
}

