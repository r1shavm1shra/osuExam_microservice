package com.osuexam.microservice.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
@Entity
public class Student {

    @Id
    @Column
    private String studentId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @ManyToMany
    @JoinColumn(name = "course_id_fk")
    List<Course> courseList;

    public Student(String studentId) {
        this.studentId = studentId;
    }
}

