package com.example.bibliosystem.entity.custom;

/**
 * Définition de la signature d'une réponse liées aux auteurs pour le service de stats
 */
public interface AuteursLus {
    long getNbPret();
    Integer getId();
    String getNom();
    String getPrenom();
}
