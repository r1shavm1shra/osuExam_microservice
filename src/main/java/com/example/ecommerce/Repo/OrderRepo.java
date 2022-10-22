package com.example.ecommerce.Repo;

import com.example.ecommerce.Model.Orders;
import com.example.ecommerce.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Orders, Long> {
}
