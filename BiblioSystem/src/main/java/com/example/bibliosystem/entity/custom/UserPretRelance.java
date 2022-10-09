package com.example.bibliosystem.entity.custom;

/**
 * Définition de la signature d'une réponse liées aux utilisateurs à relancer
 */
public interface UserPretRelance {
    String getNom();
    String getPrenom();
    String getEmail();
    long getNbPret();


}
