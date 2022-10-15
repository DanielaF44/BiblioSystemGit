package com.example.bibliosystem.repository;

import com.example.bibliosystem.entity.Pret;
import com.example.bibliosystem.entity.custom.UserPretRelance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface UserPretRepository extends JpaRepository<Pret, String> {

    @Query(value = "SELECT utilisateur.nom as nom, " +
            "              utilisateur.prenom as prenom," +
            "              utilisateur.email as email," +
            "              count(pret.id) as nbPret " +
            "       FROM pret " +
            "       INNER JOIN utilisateur " +
            "           ON pret.utilisateur_id = utilisateur.id " +
            "           AND date_fin < CURRENT_DATE " +
            "       WHERE exemplaire_ID > 0 " +
            "       GROUP BY pret.utilisateur_id, utilisateur.nom, utilisateur.prenom, utilisateur.email;",
            nativeQuery = true)
    List<UserPretRelance> findUserRelance();

}
