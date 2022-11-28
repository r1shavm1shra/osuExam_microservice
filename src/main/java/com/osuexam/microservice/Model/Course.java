package com.osuexam.microservice.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long Id;

    @Column
    private String courseName;

    @JsonManagedReference(value="course")
    @OneToOne(mappedBy="course",  targetEntity = Exam.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Exam exam;
}

