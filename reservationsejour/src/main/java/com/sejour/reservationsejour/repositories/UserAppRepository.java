package com.sejour.reservationsejour.repositories;

import com.sejour.reservationsejour.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserAppRepository extends JpaRepository<UserApp, Long> {

    UserApp findUserAppByUsername(String username);
    UserApp findUserAppById(Long id);
    
}
