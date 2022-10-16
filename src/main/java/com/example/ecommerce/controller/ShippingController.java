package com.example.ecommerce.controller;

import com.example.ecommerce.Model.ShippingDetails;
import com.example.ecommerce.Repo.ShippingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
public class ShippingController {

    @Autowired
    private ShippingRepo shippingRepo;

    @GetMapping("/users")
    public List<ShippingDetails> getAll()
    {
        return shippingRepo.findAll();
    }

    @PostMapping("/save")
    public String saveUser(@RequestBody ShippingDetails shippingDetails)
    {
        shippingRepo.save(shippingDetails);
        return "Saved...";
    }

}
