package com.example.bibliosystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BiblioRepository extends JpaRepository<Livre, String>{

    /**
     * Methode qui execute une requete SQL dans la base de données afin de retourner une liste de livres filtrés selon
     * les paramètres transmis par le service BiblioService.
     *
     * @param  titre  le titre du livre recherché
     * @param  genre le genre du livre recherché
     * @param langue  la langue du livre recherché
     *
     *
     */

    @Query(value = "SELECT * FROM livres"
                   +" INNER JOIN livre_genre ON livres.isbn = livre_genre.isbn"
                   +" INNER JOIN livre_auteur ON livres.isbn = livre_auteur.isbn"
                   +" INNER JOIN auteur ON livre_auteur.auteur_id = auteur.id"
                   +" WHERE (:titre is null OR livres.titre LIKE CONCAT('%',:titre,'%'))"
                   +" AND (:genre is null OR livre_genre.nom = :genre)"
                   +" AND (:langue is null OR livres.langue = :langue)"
                   +" AND (:auteur is null"
                   +       " OR CONCAT(auteur.nom, ' ',auteur.prenom) LIKE CONCAT('%',:auteur,'%')"
                   +       " OR CONCAT(auteur.prenom, ' ',auteur.nom) LIKE CONCAT('%',:auteur,'%')"
                   + ")", nativeQuery = true)

    List<Livre> findAllByCriteria(String titre, String auteur, String genre, String langue);
}
