package com.example.bibliosystem.repository;

import com.example.bibliosystem.entity.Pret;
import com.example.bibliosystem.entity.custom.LivresPretes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivresPretesRepository extends JpaRepository<Pret, String> {

    @Query(value = "SELECT " +
            " COUNT(pret.id) as nbPret, " +
            " livres.isbn as isbn," +
            " livres.titre as titre " +
            " FROM livres as livres" +
            "    inner join exemplaire as exemplaire on exemplaire.isbn = livres.isbn " +
            "    left join pret as pret on pret.exemplaire_id = exemplaire.id " +
            " GROUP BY livres.isbn, livres.titre " +
            " ORDER BY CASE WHEN :ordreTri <= 0 THEN COUNT(pret.id) END ASC," +
            " CASE WHEN :ordreTri > 0  THEN COUNT(pret.id) END DESC" +
            " LIMIT 10",
            nativeQuery = true)
    List<LivresPretes> findLivresPretes(Integer ordreTri);

}
