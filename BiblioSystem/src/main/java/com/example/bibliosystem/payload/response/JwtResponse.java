package com.example.bibliosystem.payload.response;
import java.util.List;

/**
 * Définition de la signature d'une réponse permettant de retourner le token d'authentification lors d'une connection
 */
public class JwtResponse {
    private String token;
    //type "Bearer" car on veut juste donner l'autorisation à n'importe quelle requête
    // qui "porte" (= qui contient) ce token dans son header
    //plus d'info sur : https://jwt.io/introduction
    private String type = "Bearer";
    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private List<String> roles;

    public JwtResponse(String accessToken, Integer id, String nom, String prenom, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public List<String> getRoles() {
        return roles;
    }
}