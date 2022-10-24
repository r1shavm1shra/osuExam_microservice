package com.osuexam.microservice.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Getter @Setter
@Entity
public class Login {

    @Id
    @Column
    private String loginId;

    @Column
    private String password;

    @Column
    private Boolean isInstructor;

}

