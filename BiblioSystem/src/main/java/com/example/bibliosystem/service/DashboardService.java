package com.example.bibliosystem.service;

import com.example.bibliosystem.AuteursLusRepository;
import com.example.bibliosystem.LivresPretesRepository;
import com.example.bibliosystem.payload.response.AuteursLusResponse;
import com.example.bibliosystem.payload.response.LivresPretesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service de statistiques de la bibliothèque
 */
@Service
public class DashboardService {
    @Autowired
    LivresPretesRepository pretRepository;

    @Autowired
    AuteursLusRepository auteurRepository;

    /**
     * Cette méthode transmet au controleur la liste des livres les plus ou moins pretes
     * @param ordreTri 0 asc / 1 desc
     * @return liste de livres
     */
    public List<LivresPretesResponse> livresPretes(Integer ordreTri) {
        return pretRepository.findLivresPretes(ordreTri);
    }

    /**
     * Cette méthode transmet au controleur la liste des auteurs les plus ou moins lus
     * @param ordreTri 0 asc / 1 desc
     * @return liste d'auterus
     */
    public List<AuteursLusResponse> auteursLus(Integer ordreTri) {
        return auteurRepository.findAuteursLus(ordreTri);
    }
}
