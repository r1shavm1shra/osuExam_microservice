package com.example.ecommerce.controller;

import com.example.ecommerce.Model.ShippingDetails;
import com.example.ecommerce.Repo.ShippingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class ShippingController {

    @Autowired
    private ShippingRepo shippingRepo;

    @GetMapping("/shipping")
    public List<ShippingDetails> getAll()
    {
        return shippingRepo.findAll();
    }

    @GetMapping("/shipping/{id}")
    public Optional<ShippingDetails> getById(@PathVariable long id)
    {
        return shippingRepo.findById(id);
    }

    @PostMapping("/saveShipping")
    public String saveUser(@RequestBody ShippingDetails shippingDetails)
    {
        shippingRepo.save(shippingDetails);
        return "Saved...";
    }

}
