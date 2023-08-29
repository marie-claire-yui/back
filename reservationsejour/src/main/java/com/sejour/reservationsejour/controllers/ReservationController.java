package com.sejour.reservationsejour.controllers;

import com.sejour.reservationsejour.entities.Reservation;
import com.sejour.reservationsejour.requests.ReservationRequest;
import com.sejour.reservationsejour.services.ReservationService;
import com.sejour.reservationsejour.services.impl.MailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {


    private ReservationService reservationService;

    private MailService mailService;



    public ReservationController(ReservationService reservationService, MailService mailService) {
        this.reservationService = reservationService;
        this.mailService = mailService;

    }



    @PostMapping
    public Reservation ajouterReservation(@RequestBody ReservationRequest reservation) throws Exception{

        return reservationService.ajouterReservation(reservation) ;

    }

    @PutMapping
    public Reservation modifierReservation(@RequestBody Reservation reservation) throws Exception{

        return reservationService.modifierReservation(reservation);
    }

    @GetMapping
    public List<Reservation> allReservations() throws Exception{
        return reservationService.allReservations();
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable Long id) throws Exception{

        return reservationService.getReservationById(id);
    }

    @DeleteMapping
    public boolean suprimerReservation(Long id) throws Exception{

        return reservationService.suprimerReservation(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> confirmerReservation(@PathVariable Long id) throws Exception{

        Reservation reservation = reservationService.confirmerReservation(id);
        return new ResponseEntity<>(reservation, HttpStatus.ACCEPTED);
    }

    @PutMapping("/annuler/{id}")
    public ResponseEntity<Object> annulerReservation(@PathVariable Long id) throws Exception
    {
        return new ResponseEntity<>(reservationService.annulerReservation(id) , HttpStatus.ACCEPTED);
    }



}
