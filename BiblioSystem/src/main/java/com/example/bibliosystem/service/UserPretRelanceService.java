package com.example.bibliosystem.service;

import com.example.bibliosystem.entity.custom.AuteursLus;
import com.example.bibliosystem.entity.custom.LivresPretes;
import com.example.bibliosystem.entity.custom.UserPretRelance;
import com.example.bibliosystem.repository.AuteursLusRepository;
import com.example.bibliosystem.repository.LivresPretesRepository;
import com.example.bibliosystem.repository.UserPretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service de gestion des relances par mail
 */
@Service
public class UserPretRelanceService {

    @Autowired
    UserPretRepository pretRepository;


    @Bean
    public List<UserPretRelance> userPretRelances() {
        return pretRepository.findUserRelance();
    }

}
