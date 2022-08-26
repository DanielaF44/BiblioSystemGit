package com.example.bibliosystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, String> {

    @Query(value="SELECT exemplaire.bibliotheque,"+
            " COUNT(exemplaire.id)  FROM exemplaire" +
            " WHERE exemplaire.pret_id is null" +
            " AND exemplaire.isbn = :isbn" +
            " GROUP BY exemplaire.bibliotheque, exemplaire.isbn", nativeQuery = true)
    List<Object> findCountExemplaire(String isbn);
}
