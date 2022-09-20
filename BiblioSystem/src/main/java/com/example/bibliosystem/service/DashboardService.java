package com.example.bibliosystem.service;

import com.example.bibliosystem.AuteursLusRepository;
import com.example.bibliosystem.LivresPretesRepository;
import com.example.bibliosystem.payload.response.AuteursLusResponse;
import com.example.bibliosystem.payload.response.LivresPretesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    @Autowired
    LivresPretesRepository pretRepository;

    @Autowired
    AuteursLusRepository auteurRepository;

    public List<LivresPretesResponse> livresPretes(Integer ordreTri) {
        return pretRepository.findLivresPretes(ordreTri);
    }

    public List<AuteursLusResponse> auteursLus(Integer ordreTri) {
        return auteurRepository.findAuteursLus(ordreTri);
    }
}
