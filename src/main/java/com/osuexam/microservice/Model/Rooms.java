package com.osuexam.microservice.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
public class Rooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long Id;

    @Column
    private Long rowCapacity;

    @Column
    private Long totalCapacity;

    @Column
    private Long offset;

    @JsonManagedReference(value="rooms")
    @OneToMany(mappedBy="rooms",  targetEntity = Seat.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Seat> seats;



}

