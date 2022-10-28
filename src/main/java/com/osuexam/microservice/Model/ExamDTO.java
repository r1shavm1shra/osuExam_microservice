package com.osuexam.microservice.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

public interface ExamDTO {

    String getStudentId();
    Long getSeatId();
    Long getExamId();
    Long getQuestionId();
    Long getIsCorrect();

}

