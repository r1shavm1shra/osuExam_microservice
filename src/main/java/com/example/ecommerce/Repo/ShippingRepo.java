package com.example.ecommerce.Repo;

import com.example.ecommerce.Model.ShippingDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepo extends JpaRepository<ShippingDetails, Long> {
}
