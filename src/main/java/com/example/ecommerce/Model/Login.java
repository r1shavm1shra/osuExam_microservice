package com.example.ecommerce.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Column
    @Getter @Setter
    private String loginId;

    @Column
    @Getter @Setter
    private String password;

}

