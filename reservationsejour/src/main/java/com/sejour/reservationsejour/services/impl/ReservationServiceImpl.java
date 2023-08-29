package com.sejour.reservationsejour.services.impl;

import com.sejour.reservationsejour.entities.Reservation;
import com.sejour.reservationsejour.entities.Sejour;
import com.sejour.reservationsejour.exceptions.ErrorMessages;
import com.sejour.reservationsejour.exceptions.NotFoundException;
import com.sejour.reservationsejour.helper.MailCommunication;
import com.sejour.reservationsejour.repositories.ReservationRepository;
import com.sejour.reservationsejour.repositories.SejourRepository;
import com.sejour.reservationsejour.requests.ReservationRequest;
import com.sejour.reservationsejour.services.ReservationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService{

    private ReservationRepository reservationRepository;

    private SejourRepository sejourRepository;

    private MailService mailService;

    public ReservationServiceImpl(ReservationRepository reservationRepository ,SejourRepository sejourRepository, MailService mailService ) {
        this.reservationRepository = reservationRepository;
        this.sejourRepository = sejourRepository;
        this.mailService=mailService;
    }
    

    @Override
    public Reservation ajouterReservation(ReservationRequest reservation) throws Exception {
    // chercher le sjour si il existe
        Long idSejour = reservation.getIdSejour();

        Sejour sejour = sejourRepository.findById(idSejour).orElse(null);


        if (sejour==null) throw  new NotFoundException(ErrorMessages.NOT_FOUND.getMessage());

            // ajout du resrvation

            Reservation dbReservation = new Reservation();
            dbReservation.setSejour(sejour);
            dbReservation.setAdresseClient(reservation.getAdresseClient());
            dbReservation.setNomClient(reservation.getNomClient());
            dbReservation.setDateReservation(reservation.getDateReservation());
            dbReservation.setPrenomClient(reservation.getPrenomClient());
            dbReservation.setNombrePersonnes(reservation.getNombrePersonnes());
            dbReservation.setMailClient(reservation.getMailClient());
           return  reservationRepository.save(dbReservation);



    }

    @Override
    public Reservation modifierReservation(Reservation reservation) throws Exception {
              Reservation dbReservation = reservationRepository.findById(reservation.getId()).orElse(null);
        if(dbReservation !=null ){
      
        dbReservation.setNomClient(reservation.getNomClient());
            dbReservation.setPrenomClient(reservation.getPrenomClient());
            dbReservation.setAdresseClient(reservation.getAdresseClient());
            dbReservation.setMailClient(reservation.getMailClient());
           // dbReservation.setSejourId(reservation.getSejour_id());
            dbReservation.setDateReservation(reservation.getDateReservation());
          //  dbReservation.setUser_id(reservation.getUser_id());

            return reservationRepository.save(dbReservation);
        }
        throw  new NotFoundException(ErrorMessages.NOT_FOUND.getMessage());
    }

    @Override
    public List<Reservation> allReservations() throws Exception {
        List<Reservation> reservations = reservationRepository.findAll();

        if (!reservations.isEmpty())   return reservations;

        throw new NotFoundException(ErrorMessages.NOT_FOUND.getMessage());

    }

    @Override
    public Reservation getReservationById(Long id_coordonnees) throws Exception {
        Reservation reservation = reservationRepository.findById(id_coordonnees).orElse(null);
        if(reservation!=null) return reservation;

        throw new NotFoundException(ErrorMessages.NOT_FOUND.getMessage());
    }

    @Override
    public boolean suprimerReservation(Long id_coordonnees) throws Exception {
       Reservation reservation = reservationRepository.findById(id_coordonnees).orElse(null);

        if (reservation!=null){
            reservationRepository.delete(reservation);
            return true;
        }

        throw new NotFoundException(ErrorMessages.NOT_FOUND.getMessage());
    }

    @Override
    public Reservation confirmerReservation(Long idReservation) throws Exception {

        Reservation reservation = reservationRepository.findById(idReservation).orElse(null);
        System.out.println();
        if (reservation==null) throw new NotFoundException(ErrorMessages.NOT_FOUND.getMessage());
        reservation.setConfirmed(true);
        reservation.setCanceled(false);
        Reservation savedReservation = reservationRepository.save(reservation);
      // l'envoie d'un email au client
        MailCommunication mailCommunication = new MailCommunication(reservation.getNomClient(), reservation.getId(), reservation.getSejour().getNom() );
        mailService.sendEmail(reservation.getMailClient(), "Confirmation !", mailCommunication.getConfirmationMessage());

        return savedReservation;
    }

    @Override
    public boolean annulerReservation(Long idReservation) throws Exception {
        Reservation reservation = reservationRepository.findById(idReservation).orElse(null);
        if(reservation!=null)
        {
            reservation.setCanceled(true);
            reservation.setConfirmed(false);
            reservationRepository.save(reservation);
            // sent mail to user
            MailCommunication mailCommunication =
                    new MailCommunication(reservation.getNomClient(), reservation.getId(), reservation.getSejour().getNom() );
            mailService.sendEmail(reservation.getMailClient(), "Annulation !", mailCommunication.getAnnulationMessage());
            return true;
        }
        throw new NotFoundException(ErrorMessages.NOT_FOUND.getMessage());
    }


}
