package com.example.bibliosystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    @Autowired
    ExemplaireRepository ExemplaireRepository;

    public List<Livre> showRecherche(String genre, String auteur, String titre, String langue){

        return BiblioRepository.findAllByCriteria(genre, auteur,titre, langue);

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

    /**
     * Cette méthode permet de transmettre au controlleur la liste des langues recupérées depuis la base de données
     *
     * @return List<Langue> retourne la liste des genres
     *
     *
     */

    @Autowired
    LangueRepository LangueRepository;
    public List<Langue> showLangue(){
        return LangueRepository.findAll();
    }

    @Autowired
    PretRepository PretRepository;
    public List<Pret> showPret(Integer utilisateurId){
        return PretRepository.findAllByUtilisateurId(utilisateurId);

    }

    public void updateDateFin(Integer pretId){
        Date dateFin = new Date();
        dateFin = PretRepository.findDateById(pretId);

        Calendar cal = Calendar.getInstance();
        cal.setTime(dateFin);

        cal.add(Calendar.DAY_OF_MONTH, 30);

        Date dateProlonge;
        dateProlonge = cal.getTime();
        System.out.println(dateProlonge);
        System.out.println(pretId);
        PretRepository.updateDateFinPret(pretId, dateProlonge);
    }

    public List<Object> countExemplaire(String isbn){
        return ExemplaireRepository.findCountExemplaire(isbn);
    }

}
