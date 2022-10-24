package com.osuexam.microservice.Repo;

import com.osuexam.microservice.Model.Course;
import com.osuexam.microservice.Model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Long> {
}
