package com.example.bibliosystem.payload.response;

/**
 * Définition de la signature d'une réponse permettant de retourner les stats sur les livres
 */
public interface LivresPretesResponse {
    long getNbPret();
    String getIsbn();
    String getTitre();

}

