package com.example.bibliosystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Langue {

    @Id
    @Column(name ="nom")
    String nom;

    public Langue(String nom) {
        this.nom = nom;
    }

    public Langue(){

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
