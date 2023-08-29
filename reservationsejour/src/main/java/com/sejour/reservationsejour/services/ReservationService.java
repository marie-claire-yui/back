package com.sejour.reservationsejour.services;

import java.util.List;

import com.sejour.reservationsejour.entities.Reservation;
import com.sejour.reservationsejour.requests.ReservationRequest;


public interface ReservationService {
    Reservation ajouterReservation(ReservationRequest reservation) throws Exception;
    Reservation modifierReservation(Reservation reservation) throws Exception;
    List<Reservation> allReservations() throws Exception;
    Reservation getReservationById(Long id_coordonnees) throws Exception;
    boolean suprimerReservation(Long id_coordonnees) throws Exception;

    Reservation confirmerReservation(Long idReservation) throws  Exception;

    boolean annulerReservation(Long idReservation) throws Exception;
}
