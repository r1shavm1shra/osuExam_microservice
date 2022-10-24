package com.osuexam.microservice.controller;

import com.osuexam.microservice.Model.Exam;
import com.osuexam.microservice.Model.Seat;
import com.osuexam.microservice.Model.StudentResponse;
import com.osuexam.microservice.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
public class SeatController {

    @Autowired
    private SeatRepo seatRepo;

    @PostMapping("/assignSeats")
    public Map<String, String> assignSeat(@RequestBody Seat seat)
    {
        Map<String, String> response = new HashMap<>();
        seatRepo.saveAndFlush(seat);
        response.put("Message","Successfully saved");
        return response;
    }
}

