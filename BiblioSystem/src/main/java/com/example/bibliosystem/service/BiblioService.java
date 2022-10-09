package com.example.bibliosystem.service;

import com.example.bibliosystem.entity.Genre;
import com.example.bibliosystem.entity.Langue;
import com.example.bibliosystem.entity.Livre;
import com.example.bibliosystem.entity.Pret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BiblioService {
    @Autowired
    com.example.bibliosystem.repository.BiblioRepository BiblioRepository;

    /**
     * Cette méthode permet de transmettre au controleur la liste des livres recupérés depuis la base de données
     * @return List<Livre> retourne la liste des livres
     */
    public List<Livre> showLivre(){
        return BiblioRepository.findAll();
    }

    /**
     * Cette méthode permet d'ajouter un livre
     */
    public void addLivre(Livre newLivre){

        BiblioRepository.save(newLivre);
    }

    /**
     * Cette méthode permet de transmettre au controleur la liste des livres qui a été filtrée à travers la méthode
     * findAllByCriteria définie dans BiblioRepository
     * @return List<Livre> retourne la liste des livres
     */
    @Autowired
    com.example.bibliosystem.repository.ExemplaireRepository ExemplaireRepository;

    public List<Livre> showRecherche(String genre, String auteur, String titre, String langue){

        return BiblioRepository.findAllByCriteria(genre, auteur,titre, langue);

    }

    @Autowired
    com.example.bibliosystem.repository.GenreRepository GenreRepository;

    /**
     * Cette méthode permet de transmettre au controleur la liste des genres recupérés depuis la base de données
     * @return List<Genre> retourne la liste des genres
     */
    public List<Genre> showGenre(){
        return GenreRepository.findAll();
    }


    @Autowired
    com.example.bibliosystem.security.LangueRepository LangueRepository;

    /**
     * Cette méthode permet de transmettre au controleur la liste des langues recupérées depuis la base de données
     * @return List<Langue> retourne la liste des genres
     */
    public List<Langue> showLangue(){
        return LangueRepository.findAll();
    }

    @Autowired
    com.example.bibliosystem.repository.PretRepository PretRepository;
    /**
     * Cette méthode permet de transmettre au controleur la liste des prêts recupérées depuis la base de données
     * pour un utilisateur
     * @return liste de prêts
     */
    public List<Pret> showPret(Integer utilisateurId){
        return PretRepository.findAllByUtilisateurId(utilisateurId);

    }

    /**
     * Cette méthode permet de mettre à jour la date de fin d'un prêt
     * @param pretId ID du prêt
     */
    public void updateDateFin(Integer pretId){
        Date dateFin = new Date();
        // on récupère la date de fin actuelle du prêt
        dateFin = PretRepository.findDateById(pretId);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateFin);

        //on ajoute 30 jours
        cal.add(Calendar.DAY_OF_MONTH, 30);
        Date dateProlonge;
        dateProlonge = cal.getTime();

        //on appelle la méthode de mise à jour
        PretRepository.updateDateFinPret(pretId, dateProlonge);
    }


    /**
     * Cette méthode permet de récupérer le nombre d'exemplaires disponibles pour un livre donné
     * @param isbn l'Isbn d'un livre
     * @return Liste d'exemplaires disponibles par bibliothèque
     */
    public List<Object> countExemplaire(String isbn){
        return ExemplaireRepository.findCountExemplaire(isbn);
    }

}
