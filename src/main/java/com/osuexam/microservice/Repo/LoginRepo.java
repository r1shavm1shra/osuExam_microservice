package com.osuexam.microservice.Repo;

import com.osuexam.microservice.Model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepo extends JpaRepository<Login, String> {
}
