package com.example.bibliosystem.repository;

import com.example.bibliosystem.entity.Pret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface PretRepository extends JpaRepository<Pret, String> {

    @Query(value = "SELECT * FROM pret "
            + "WHERE (pret.utilisateur_id = :utilisateurId) "
            + "AND (pret.date_fin >= CURRENT_DATE) "
            + "AND (pret.date_debut <= CURRENT_DATE)",
            nativeQuery = true)
    List<Pret> findAllByUtilisateurId(Integer utilisateurId);

    @Query(value = "SELECT date_fin FROM pret WHERE (pret.id = :pretId)", nativeQuery = true)
    Date findDateById(Integer pretId);

    //TODO : bug à corriger : il faut un join avec le pret et l'utisateur
    // pour verifier que l'utilisateur prolonge un pret qui est bien à lui
    @Transactional
    @Modifying
    @Query(value = "UPDATE pret SET date_fin = :dateProlonge, renouvele = true WHERE (pret.id = :pretId)", nativeQuery = true)
    void updateDateFinPret(Integer pretId, Date dateProlonge);

}