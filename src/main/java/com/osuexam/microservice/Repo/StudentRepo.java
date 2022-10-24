package com.osuexam.microservice.Repo;

import com.osuexam.microservice.Model.Login;
import com.osuexam.microservice.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, String> {
}
