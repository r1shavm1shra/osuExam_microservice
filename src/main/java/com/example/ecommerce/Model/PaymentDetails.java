package com.example.ecommerce.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Column
    @Getter @Setter
    private String creditCardNum;

    @Column
    @Getter @Setter
    private String cvv;

    @Column
    @Getter @Setter
    private String cardName;

    @Column
    @Getter @Setter
    private String date;

}

