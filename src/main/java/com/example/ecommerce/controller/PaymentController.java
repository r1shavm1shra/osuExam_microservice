package com.example.ecommerce.controller;

import com.example.ecommerce.Model.PaymentDetails;
import com.example.ecommerce.Repo.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
public class PaymentController {
    @Autowired
    private PaymentRepo paymentRepo;

    @GetMapping("/payment")
    public List<PaymentDetails> getAll()
    {
        return paymentRepo.findAll();
    }

    @GetMapping("/payment/{id}")
    public Optional<PaymentDetails> getById(@PathVariable long id)
    {
        return paymentRepo.findById(id);
    }

    @PostMapping("/savePayment")
    public Map<String, String> saveUser(@RequestBody PaymentDetails paymentDetails)
    {
        paymentDetails = paymentRepo.saveAndFlush(paymentDetails);
        Map<String,String> response = new HashMap<>();
        response.put("id",""+paymentDetails.getId());
        return response;
    }

}
