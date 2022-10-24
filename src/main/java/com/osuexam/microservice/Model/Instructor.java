package com.osuexam.microservice.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter @Setter
@Entity
public class Instructor {

    @Id
    @Column
    private Long Id;

    @Column
    private String firstName;

    @Column
    private String lastName;
}

