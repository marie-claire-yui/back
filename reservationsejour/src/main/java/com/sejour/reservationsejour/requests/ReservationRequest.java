package com.sejour.reservationsejour.requests;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class ReservationRequest implements Serializable {


    private String nomClient;
    private String prenomClient;
    private String adresseClient;
    private String mailClient;
    private Long idSejour;

    private Date dateReservation;

    private Integer nombrePersonnes;


}
