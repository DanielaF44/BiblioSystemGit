package com.example.bibliosystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiblioService {
    @Autowired
    BiblioRepository BiblioRepository;

    /**
     * Cette méthode permet de transmettre au controlleur la liste des livres recupérés depuis la base de données
     *
     * @return List<Livre> retourne la liste des livres
     *
     *
     */

    public List<Livre> showLivre(){
        return BiblioRepository.findAll();
    }

    public void addLivre(Livre newLivre){

        BiblioRepository.save(newLivre);
    }

    /**
     * Cette méthode permet de transmettre au controlleur la liste des livres qui a été filtrée à travers la méthode
     * finAllByCriteria définie dans BiblioRepository
     *
     * @return List<Livre> retourne la liste des livres
     *
     *
     */

    public List<Livre> showRecherche(String genre, String titre, String langue){
        return BiblioRepository.findAllByCriteria(genre, titre, langue);

    }

    @Autowired
    GenreRepository GenreRepository;

    /**
     * Cette méthode permet de transmettre au controlleur la liste des genres recupérés depuis la base de données
     *
     * @return List<Genre> retourne la liste des genres
     *
     *
     */
    public List<Genre> showGenre(){
        return GenreRepository.findAll();
    }

}
