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
    private StudentRepo studentRepo;

    @Autowired
    private AnswerKeyRepo answerKeyRepo;

    @Autowired
    private RoomsRepo roomsRepo;

    @Autowired
    private SeatRepo seatRepo;

    @Autowired
    private StudentResponseRepo studentResponseRepo;

    @PostMapping("/createExamAndAllocateRoom")
    public Map<String, String> createExam(@RequestBody Exam exam)
    {
        Map<String, String> response = new HashMap<>();
        exam.setAnswerKey(answerKeyRepo.saveAllAndFlush(exam.getAnswerKey()));
        exam.setRooms(roomsRepo.saveAndFlush(exam.getRooms()));
        exam = examRepo.saveAndFlush(exam);

        List<String> studentIds = studentRepo.findAllStudentIdByCourseId(exam.getCourse().getId());
        List<Seat> seatList = new ArrayList<>();
        for(String studentId: studentIds)
            seatList.add(new Seat(exam.getRooms(),new Student(studentId)));

        seatRepo.saveAllAndFlush(seatList);
        response.put("Message","Successfully saved");
        response.put("ExamId",""+exam.getId());
        response.put("RoomId",""+exam.getRooms().getId());
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

    @GetMapping("/getExamById/{id}")
    public Optional<Exam> getById(@PathVariable long id)
    {
        return examRepo.findById(id);
    }

    @GetMapping("/getAllStudentResponseForExam/{id}")
    public List<ExamDTO> getStudentResponseByExamId(@PathVariable long id)
    {
        return examRepo.findAllStudentResponseForExam(id);
    }

    @GetMapping("/getNearByStudentResponseForExam/{roomId}/{seatId}/{questionId}")
    public List<ExamDTO> getNearByStudentResponseForExam(@PathVariable long roomId, @PathVariable long seatId, @PathVariable long questionId)
    {
        return examRepo.findNearByStudentResponseForExam(seatId, roomId, questionId);
    }

}

