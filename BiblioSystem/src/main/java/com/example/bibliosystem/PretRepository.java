package com.example.bibliosystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PretRepository extends JpaRepository<Pret, String> {

    @Query(value= "SELECT * FROM pret "
                 +"WHERE (pret.utilisateur_id = :utilisateurId) "
                 +"AND (pret.date_fin >= CURRENT_DATE) "
                 +"AND (pret.date_debut <= CURRENT_DATE)",
           nativeQuery = true)
    List<Pret> findAllByUtilisateurId(Integer utilisateurId);
}
