package com.example.bibliosystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Editeur {

    @Id
    @Column(name = "id")
    int id;

    @Column(name = "nom")
    String nom;

    public Editeur(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Editeur(){

    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
}
