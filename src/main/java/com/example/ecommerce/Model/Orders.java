package com.example.ecommerce.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "payment_details_id_fk")
    private PaymentDetails paymentDetails;

    @OneToOne
    @JoinColumn(name = "shipping_details_id_fk")
    private ShippingDetails shippingDetails;

    @ManyToMany
    @JoinColumn(name = "Inventory_id_fk")
    List<LineItem> items;

}

