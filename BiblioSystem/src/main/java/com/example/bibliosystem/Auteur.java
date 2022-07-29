package com.example.bibliosystem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Auteur {
    @Id
    @Column(name ="id")
    int id;

    @Column(name = "nom")
    String nom;

    @Column(name = "prenom")
    String prenom;

    public Auteur(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Auteur(){

    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
}
