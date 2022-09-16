package com.example.bibliosystem;

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

    @Transactional
    @Modifying
    @Query(value = "UPDATE pret SET date_fin = :dateProlonge, renouvele = true WHERE (pret.id = :pretId)", nativeQuery = true)
    void updateDateFinPret(Integer pretId, Date dateProlonge);


    @Query(value = "SELECT\n" +
            "    COUNT(pret.id) AS nbPret, \n" +
            "    livres.isbn,\n" +
            "    livres.titre\n" +
            "FROM livres\n" +
            "    inner join exemplaire on exemplaire.isbn = livres.isbn\n" +
            "    inner join pret on pret.exemplaire_id = exemplaire.id\n" +
            "GROUP BY livres.isbn, livres.titre\n" +
            "ORDER by nbPret DESC\n" +
            "LIMIT 10",
            nativeQuery = true)
    List<Object> findLivresPlusPretes();

}