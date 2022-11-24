package com.osuexam.microservice.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
public class Login {

    @Id
    @Column
    private String loginId;

    @Column
    private String password;

    @Column
    private Character loginType;

    @OneToOne
    @JoinColumn(name = "student_id_fk")
    Student student;

    @OneToOne
    @JoinColumn(name = "instructor_id_fk")
    Instructor instructor;

}

