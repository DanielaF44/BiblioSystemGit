package com.example.bibliosystem;

import com.example.bibliosystem.payload.response.LivresPlusPretesResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivresPlusPretesRepository extends JpaRepository<Pret, String> {

    @Query(value = "SELECT " +
            " COUNT(pret.id) as nbPret, " +
            " livres.isbn as isbn," +
            " livres.titre as titre " +
            " FROM livres as livres" +
            "    inner join exemplaire as exemplaire on exemplaire.isbn = livres.isbn " +
            "    inner join pret as pret on pret.exemplaire_id = exemplaire.id " +
            "GROUP BY livres.isbn, livres.titre " +
            "ORDER by nbPret DESC " +
            " LIMIT 10",
            nativeQuery = true)
    List<LivresPlusPretesResponse> findLivresPlusPretes();

}
