package com.osuexam.microservice.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
public class Instructor {

    @Id
    @Column
    private String instructorId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @ManyToMany
    @JoinColumn(name = "course_id_fk")
    List<Course> courseList;
}

