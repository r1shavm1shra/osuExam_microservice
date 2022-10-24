package com.osuexam.microservice.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class StudentResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long Id;

    @Column
    private String Answer;

    @JsonBackReference(value="answer-key")
    @OneToOne
    @JoinColumn(name="answer_key_id_fk")
    private AnswerKey answerKey;

    @JsonBackReference(value="seats")
    @ManyToOne
    @JoinColumn(name = "seat_id_fk")
    private Seat seats;
}

