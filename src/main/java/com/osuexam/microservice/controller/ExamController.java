package com.osuexam.microservice.controller;

import com.osuexam.microservice.Model.*;
import com.osuexam.microservice.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
public class ExamController {

    @Autowired
    private ExamRepo examRepo;

    @Autowired
    private AnswerKeyRepo answerKeyRepo;

    @Autowired
    private RoomsRepo roomsRepo;

    @Autowired
    private SeatRepo seatRepo;

    @Autowired
    private StudentResponseRepo studentResponseRepo;

    @PostMapping("/createExam")
    public Map<String, String> createExam(@RequestBody Exam exam)
    {
        Map<String, String> response = new HashMap<>();
        exam.setAnswerKey(answerKeyRepo.saveAllAndFlush(exam.getAnswerKey()));
        exam.setRooms(roomsRepo.saveAndFlush(exam.getRooms()));
        examRepo.saveAndFlush(exam);
        response.put("Message","Successfully saved");
        return response;
    }

    @PostMapping("/saveStudentResponse")
    public Map<String, String> saveStudentResponse(@RequestBody StudentResponse studentResponse)
    {
        Map<String, String> response = new HashMap<>();
        studentResponseRepo.save(studentResponse);
        response.put("Message","Successfully saved");
        return response;
    }

    @GetMapping("/exam/{id}")
    public Optional<Exam> getById(@PathVariable long id)
    {
        return examRepo.findById(id);
    }

    @GetMapping("/getStudentResponseByExamId/{id}")
    public List<ExamDTO> getStudentResponseByExamId(@PathVariable long id)
    {
        return examRepo.findAllStudentResponseForExam(id);
    }

    @GetMapping("/getNearByStudentResponseForExam/examId={examId}&studentIds={studentIds}")
    public List<ExamDTO> getNearByStudentResponseForExam(@PathVariable long examId, @PathVariable String studentIds)
    {
        List<String> studentIdList = Arrays.asList(studentIds.split(","));
        return examRepo.findNearByStudentResponseForExam(examId,studentIdList);
    }

}

