package com.example.bibliosystem.payload.response;

/**
 * Définition de la signature d'une réponse liées aux auteurs pour le service de stats
 */
public interface AuteursLusResponse {
    long getNbPret();
    Integer getId();
    String getNom();
    String getPrenom();
}
