package com.osuexam.microservice.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamDTO {

    private String studentId;
    private String seatId;
    private String examId;
    private String questionId;
    private String actualAnswer;
    private String studentAnswer;


}

