package com.example.ecommerce.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class ShippingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Column
    @Getter @Setter
    private String Name;

    @Column
    @Getter @Setter
    private String AddressLine1;

    @Column
    @Getter @Setter
    private String AddressLine2;

    @Column
    @Getter @Setter
    private String City;

    @Column
    @Getter @Setter
    private String State;

    @Column
    @Getter @Setter
    private String ZipCode;
}

