package com.example.ecommerce.Repo;

import com.example.ecommerce.Model.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<PaymentDetails, Long> {
}
