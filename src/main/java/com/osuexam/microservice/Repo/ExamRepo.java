package com.osuexam.microservice.Repo;

import com.osuexam.microservice.Model.Exam;
import com.osuexam.microservice.Model.ExamDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamRepo extends JpaRepository<Exam, Long> {
    @Query(
            value = "SELECT s.student_id_fk as 'studentId', s.id as 'seatId',e.id as 'examId',a.question_id as 'questionId', " +
                    "a.answer = sr.answer as 'IsCorrect' " +
                    "FROM Exam e inner join Answer_Key a on e.id = a.answer_key_id_fk " +
                    "inner join rooms r on e.rooms_key_id_fk = r.id " +
                    "inner join seat s on s.rooms_id_fk = r.id " +
                    "inner join student_Response sr on sr.seat_id_fk = s.id and sr.answer_key_id_fk = a.question_id " +
                    "where e.id=?1",
            nativeQuery = true)
    List<ExamDTO> findAllStudentResponseForExam(Long id);

    @Query(
            value = "SELECT s.student_id_fk as 'studentId', s.id as 'seatId',e.id as 'examId',a.question_id as 'questionId', " +
                    "a.answer = sr.answer as 'IsCorrect' " +
                    "FROM Exam e inner join Answer_Key a on e.id = a.answer_key_id_fk " +
                    "inner join rooms r on e.rooms_key_id_fk = r.id " +
                    "inner join seat s on s.rooms_id_fk = r.id " +
                    "inner join student_Response sr on sr.seat_id_fk = s.id and sr.answer_key_id_fk = a.question_id " +
                    "where e.id=:examId and s.student_id_fk IN (:studentIds)",
            nativeQuery = true)
    List<ExamDTO> findNearByStudentResponseForExam(@Param("examId") Long examId,@Param("studentIds") List<String> studentIds);

}
