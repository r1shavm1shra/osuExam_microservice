package com.osuexam.microservice.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @JsonBackReference(value="rooms")
    @ManyToOne
    @JoinColumn(name = "rooms_id_fk")
    private Rooms rooms;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "student_id_fk")
    private Student student;

    @JsonManagedReference(value="seats")
    @OneToMany(mappedBy="seats",  targetEntity = StudentResponse.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<StudentResponse> studentResponseList;
}

