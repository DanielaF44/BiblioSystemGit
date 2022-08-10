package com.example.bibliosystem;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Classe nécéssaire pour pouvoir récuperer les entrées en base de données depuis la table "langue"
 *
 *
 */
public interface LangueRepository extends JpaRepository<Langue, String> {

}
