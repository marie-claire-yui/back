package com.sejour.reservationsejour.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class MailCommunication {

    private String nomClient;
    private Long idReservation;
    private  String nomSejour;

    public  String getConfirmationMessage(){

        return "Bonjour, "+nomClient+ " votre reservation " + idReservation +" pour le sejour "+nomSejour + " est Confirmé !" +
                "\n"+
                "Bonne vacances .";

    }

    public  String getAnnulationMessage(){

        return "Bonjour, "+nomClient+ " votre reservation " + idReservation +" pour le sejour "+nomSejour + " est Annulé !" +
                "\n"+
                "veuillez passer par une nouvelle reservation si besoin .";

    }




}
