package com.example.bibliosystem.repository;

import com.example.bibliosystem.entity.Auteur;
import com.example.bibliosystem.payload.response.AuteursLusResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuteursLusRepository extends JpaRepository<Auteur, String> {

    @Query(value = "SELECT " +
            " COUNT(pret.id) AS nbPret, " +
            " auteur.id as id, " +
            " auteur.nom as nom, " +
            " auteur.prenom as prenom " +
            "FROM auteur " +
            "    INNER JOIN livre_auteur ON livre_auteur.auteur_id = auteur.id " +
            "    INNER JOIN livres ON livres.isbn = livre_auteur.isbn " +
            "    INNER JOIN exemplaire ON exemplaire.isbn = livres.isbn " +
            "    LEFT JOIN pret ON pret.exemplaire_id = exemplaire.id " +
            "GROUP BY auteur.id, auteur.nom, auteur.prenom " +
            "ORDER BY CASE WHEN :ordreTri <= 0 THEN COUNT(pret.id) END ASC, " +
            " CASE WHEN :ordreTri > 0  THEN COUNT(pret.id) END DESC " +
            " LIMIT 10 ",
            nativeQuery = true)
    List<AuteursLusResponse> findAuteursLus(Integer ordreTri);

}
