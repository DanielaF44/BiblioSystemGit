package com.example.bibliosystem.payload.request;


/**
 * Définition de la signature d'une requête de connexion d'un utilisateur
 */
public class LoginRequest {

    //en réalité on authentifie l'utilisateur avec son adresse email => username = email
    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}