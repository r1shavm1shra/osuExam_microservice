package com.osuexam.microservice.Repo;

import com.osuexam.microservice.Model.Login;
import com.osuexam.microservice.Model.Student;
import com.osuexam.microservice.Model.StudentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
public interface StudentResponseRepo extends JpaRepository<StudentResponse, Long> {
}
