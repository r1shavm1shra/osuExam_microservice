package com.osuexam.microservice.Repo;

import com.osuexam.microservice.Model.Login;
import com.osuexam.microservice.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, String> {
    @Query(
            value = "SELECT student_student_id from student_course_list where course_list_id = ?1",
            nativeQuery = true)
    List<String> findAllStudentIdByCourseId(Long courseId);
}
