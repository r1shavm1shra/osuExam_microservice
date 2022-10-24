package com.osuexam.microservice.Repo;

import com.osuexam.microservice.Model.AnswerKey;
import com.osuexam.microservice.Model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerKeyRepo extends JpaRepository<AnswerKey, String> {
}
