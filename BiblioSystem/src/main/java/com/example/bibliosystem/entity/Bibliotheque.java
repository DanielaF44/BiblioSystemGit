package com.example.bibliosystem.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bibliotheque")
public class Bibliotheque {

    @Id
    @Column(name = "nom")
    private String nom;

    public Bibliotheque(){

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
