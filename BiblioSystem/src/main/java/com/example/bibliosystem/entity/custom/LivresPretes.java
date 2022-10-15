package com.example.bibliosystem.entity.custom;

/**
 * Définition de la signature d'une réponse permettant de retourner les stats sur les livres
 */
public interface LivresPretes {
    long getNbPret();
    String getIsbn();
    String getTitre();

}

