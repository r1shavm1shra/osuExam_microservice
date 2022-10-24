package com.osuexam.microservice.Repo;

import com.osuexam.microservice.Model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepo extends JpaRepository<Seat, Long> {
}
