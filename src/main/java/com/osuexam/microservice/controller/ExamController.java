package com.osuexam.microservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.osuexam.microservice.Model.*;
import com.osuexam.microservice.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
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

    @PostMapping("/saveStudentResponse/{roomId}/{seatId}/{questionId}")
    public Map<String, String> saveStudentResponse(@PathVariable long roomId, @PathVariable long seatId, @PathVariable long questionId,@RequestBody StudentResponse studentResponse) throws JsonProcessingException, URISyntaxException {
        Map<String, String> response = new HashMap<>();
        studentResponseRepo.save(studentResponse);
        getStudentResponseForExam(roomId,seatId,questionId);
        response.put("Message","Successfully saved");
        return response;
    }

    @GetMapping("/getExamById/{id}")
    public Optional<Exam> getById(@PathVariable long id)
    {
        return examRepo.findById(id);
    }

    @GetMapping("/getAllStudentResponseForExam/{id}")
    public Map<String,List<Pair<String,String>>> getStudentResponseByExamId(@PathVariable long id)
    {
        List<ExamDTO> examDTOList  =  examRepo.findAllStudentResponseForExam(id);
        Map<String,List<Pair<String,String>>> responseList = new HashMap<>();
        Map<Long,Integer> map = new HashMap<>();
        String answer;
        for(ExamDTO examDTO: examDTOList) {
            if (examDTO.getIsCorrect() == 0)
                map.merge(examDTO.getQuestionId(), 1, Integer::sum);
        }
        for(ExamDTO examDTO: examDTOList) {
            answer = examDTO.getIsCorrect() == 0? map.get(examDTO.getQuestionId())>=3 ? "Malpractice":"Incorrect":"Correct";
            responseList.computeIfAbsent(examDTO.getStudentId(),x->new ArrayList<>()).add(Pair.of(examDTO.getQuestionId()+"",answer));
        }
        return responseList;
    }

    @GetMapping("/getNearByStudentResponseForExam/{roomId}/{seatId}/{questionId}")
    public Map<String,List<Pair<String,String>>> getNearByStudentResponseForExam(@PathVariable long roomId, @PathVariable long seatId, @PathVariable long questionId) throws JsonProcessingException, URISyntaxException {
        return getStudentResponseForExam(roomId,seatId,questionId);
    }

    private Map<String,List<Pair<String,String>>> getStudentResponseForExam(@PathVariable long roomId, @PathVariable long seatId, @PathVariable long questionId) throws JsonProcessingException, URISyntaxException {
        List<ExamDTO> examDTOList  =  examRepo.findNearByStudentResponseForExam(seatId, roomId, questionId);
        Map<String,List<Pair<String,String>>> responseList = new HashMap<>();
        int inCorrect =0;
        String answer;
        for(ExamDTO examDTO: examDTOList) {
            inCorrect++;
        }
        for(ExamDTO examDTO: examDTOList) {
            answer = examDTO.getIsCorrect() == 0? inCorrect>=3 ? "Malpractice":"Incorrect":"Correct";
            responseList.computeIfAbsent(examDTO.getStudentId(),x->new ArrayList<>()).add(Pair.of(examDTO.getQuestionId()+"",answer));
        }
        final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("ws://localhost:80/WebSocket"));
        clientEndPoint.sendMessage(new ObjectMapper().writeValueAsString(responseList));
        return responseList;

    }

}

