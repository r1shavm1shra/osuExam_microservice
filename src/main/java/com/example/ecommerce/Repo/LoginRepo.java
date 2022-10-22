package com.example.ecommerce.Repo;

import com.example.ecommerce.Model.Login;
import com.example.ecommerce.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepo extends JpaRepository<Login, Long> {
}
