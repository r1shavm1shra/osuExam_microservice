package com.osuexam.microservice.controller;

import com.osuexam.microservice.Model.Course;
import com.osuexam.microservice.Model.Exam;
import com.osuexam.microservice.Model.Seat;
import com.osuexam.microservice.Model.StudentResponse;
import com.osuexam.microservice.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
public class CourseController {

    @Autowired
    private CourseRepo courseRepo;

    @PostMapping("/createCourse")
    public Map<String, String> createExam(@RequestBody List<Course> course)
    {
        Map<String, String> response = new HashMap<>();
        course = courseRepo.saveAllAndFlush(course);
        response.put("Message","Successfully saved");
        return response;
    }

    @GetMapping("/getCourseById/{id}")
    public Optional<Course> getById(@PathVariable long id)
    {
        return courseRepo.findById(id);
    }

    @GetMapping("/getAllCourses")
    public List<Course> getAll()
    {
        return courseRepo.findAll();
    }

}

