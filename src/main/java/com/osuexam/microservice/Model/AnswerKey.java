package com.osuexam.microservice.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class AnswerKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long QuestionId;

    @Column
    private String Answer;
}

