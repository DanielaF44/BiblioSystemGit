package com.example.bibliosystem;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Classe nécéssaire pour pouvoir récuperer les entrées en base de données depuis la table "genre"
 *
 *
 */

public interface GenreRepository extends JpaRepository<Genre, String> {
}
