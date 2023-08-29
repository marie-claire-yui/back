package com.sejour.reservationsejour.controllers;

import com.sejour.reservationsejour.entities.Sejour;
import com.sejour.reservationsejour.entities.UserApp;
import com.sejour.reservationsejour.services.UserAppService;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserAppController {

    private UserAppService userAppService;

    public UserAppController(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    @PostMapping
    public ResponseEntity<UserApp> addUser(@RequestBody UserApp userApp) throws Exception{

        UserApp user = userAppService.ajouterUser(userApp);

        return  new ResponseEntity<>(user, HttpStatus.CREATED);

    }


    // get All Users
    @GetMapping
    public ResponseEntity<List<UserApp>> getAllUsers() throws  Exception{

        return  new ResponseEntity<>(userAppService.getAllUsers(), HttpStatus.OK);
    }


    // get by id
    @GetMapping("/{id}")

    public ResponseEntity<UserApp> getUserById(@PathVariable Long id) throws  Exception{

        return new ResponseEntity<>(userAppService.getUserById(id), HttpStatus.OK);
    }

// update un user

    @PutMapping
    public ResponseEntity<UserApp> modifierUser(@RequestBody UserApp userApp) throws Exception{

         UserApp user = userAppService.modifierUser(userApp);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    // supprimer un sejour par Id

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> supprimerUserById(@PathVariable Long id) throws Exception
    {
        Boolean user = userAppService.supprimerUserById(id);
        // HttpStatus status = user ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    return new ResponseEntity<>(user, HttpStatus.OK);
    }
   
}
