package com.sejour.reservationsejour.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sejour.reservationsejour.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
}
