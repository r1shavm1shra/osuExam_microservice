package com.osuexam.microservice.Repo;

import com.osuexam.microservice.Model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepo extends JpaRepository<Instructor, Long> {


}
