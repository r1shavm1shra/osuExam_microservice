package com.example.ecommerce.controller;

import com.example.ecommerce.Model.Orders;
import com.example.ecommerce.Model.ShippingDetails;
import com.example.ecommerce.Repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@CrossOrigin
@RestController
public class OrderControllers {
    @Autowired
    private OrderRepo orderRepo;

    @GetMapping("/orders")
    public List<Orders> getAll()
    {
        return orderRepo.findAll();
    }

    @GetMapping("/orders/id")
    public Optional<Orders> getById(@PathVariable long id)
    {
        return orderRepo.findById(id);
    }

    @PostMapping("/saveOrder")
    public Map<String, String> saveOrder(@RequestBody Orders order)
    {
        order = orderRepo.save(order);
        Map<String,String> response = new HashMap<>();
        response.put("id",""+order.getId());
        return response;
    }

    @DeleteMapping("/orderDelete/{id}")
    public String deleteOrder(@PathVariable long id)
    {
        orderRepo.deleteById(id);
        return "deleted...";
    }

}
