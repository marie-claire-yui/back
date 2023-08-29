package com.sejour.reservationsejour.services.impl;

import com.sejour.reservationsejour.entities.UserApp;
import com.sejour.reservationsejour.exceptions.AlreadyExistException;
import com.sejour.reservationsejour.exceptions.ErrorMessages;
import com.sejour.reservationsejour.exceptions.NotFoundException;
import com.sejour.reservationsejour.repositories.UserAppRepository;
import com.sejour.reservationsejour.services.UserAppService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAppServiceImpl implements UserAppService {

    private UserAppRepository userAppRepository;

    public UserAppServiceImpl(UserAppRepository userAppRepository) {
        this.userAppRepository = userAppRepository;
    }

    @Override
    public UserApp ajouterUser(UserApp userApp) throws Exception {

        // chrcher si l'utilisateur deja exist
        UserApp user = userAppRepository.findUserAppByUsername(userApp.getUsername());
        if (user!=null) throw new AlreadyExistException(ErrorMessages.ALREADY_EXIST.getMessage());

        // l'ajout de l'utilisateur
            return userAppRepository.save(userApp);

    }

    @Override
    public UserApp modifierUser(UserApp userApp) throws Exception {

        UserApp user = userAppRepository.findUserAppByUsername(userApp.getUsername());
        if (user !=null){
            user.setNom(userApp.getNom());
            user.setPassword(userApp.getPassword());
            user.setPrenom(userApp.getPrenom());
            return  userAppRepository.save(user);
        }
        throw new NotFoundException(ErrorMessages.NOT_FOUND.getMessage());
    }

    @Override
    public UserApp getUserById(Long id) throws Exception {
        UserApp userApp = userAppRepository.findUserAppById(id);
        if (userApp!=null) return userApp;
        throw  new NotFoundException(ErrorMessages.NOT_FOUND.getMessage());
    }

    @Override
    public List<UserApp> getAllUsers() throws Exception {

        List<UserApp> users = userAppRepository.findAll();
        if (!users.isEmpty()) return users;
        throw new NotFoundException(ErrorMessages.NOT_FOUND.getMessage());
    }

    @Override
    public boolean supprimerUserById(Long id) throws Exception {

        UserApp user = getUserById(id);
        userAppRepository.delete(user);
        return true;
    }
}
