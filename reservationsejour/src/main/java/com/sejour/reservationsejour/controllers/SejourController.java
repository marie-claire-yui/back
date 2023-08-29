package com.sejour.reservationsejour.controllers;

import com.sejour.reservationsejour.entities.Sejour;
import com.sejour.reservationsejour.services.SejourService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sejour")
public class SejourController {


    // l'injection des dependences

    private SejourService sejourService;

    public SejourController(SejourService sejourService) {
        this.sejourService = sejourService;
    }
    // ajouter un sejour / il faut le POST

    @PostMapping
    public ResponseEntity<Sejour>  ajouterSejour(@RequestBody Sejour sejour) throws Exception{

        Sejour sejour1 = sejourService.ajouterSejour(sejour);


        return new ResponseEntity<>(sejour1, HttpStatus.CREATED);
    }


    // recuperer un sejour par ID

    @GetMapping("/{id}")
    public ResponseEntity<Sejour> getSejourById(@PathVariable Long id) throws Exception{

        Sejour sejour   = sejourService.sejouRbyId(id);
        return  new ResponseEntity<>(sejour, HttpStatus.OK) ;
    }


    // recuperer tous les Sejours

    @GetMapping
    public ResponseEntity<List<Sejour>> getAllSejour() throws Exception{

        List<Sejour> sejour = sejourService.allSejours();
        return  new ResponseEntity<>(sejour, HttpStatus.OK) ;
    }


    // update un Sejour

    @PutMapping
    public ResponseEntity<Sejour> modifierSejour(@RequestBody Sejour sejour) throws Exception{

        Sejour sejourM = sejourService.modifierSejour(sejour);
        return new ResponseEntity<>(sejourM, HttpStatus.OK);
    }


    // supprimer un sejour par Id

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> supprimerSejourById(@PathVariable Long id) throws Exception
    {
        Boolean sejour = sejourService.suprimerSejour(id);
    return new ResponseEntity<>(sejour, HttpStatus.OK);
    }
}
