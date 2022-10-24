package com.osuexam.microservice.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long Id;

    @OneToMany
    @JoinColumn(name = "answer_key_id_fk")
    List<AnswerKey> answerKey;

    @OneToOne
    @JoinColumn(name = "rooms_key_id_fk")
    Rooms rooms;

    @JsonBackReference(value="course")
    @ManyToOne
    @JoinColumn(name="course_id_fk")
    Course course;
}

