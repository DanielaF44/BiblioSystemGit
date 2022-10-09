package com.example.bibliosystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class Genre {
    @Id
    String nom;

    public Genre(String nom) {
        this.nom = nom;
    }

    public Genre() {

    }

    public String getNom() {
        return nom;
    }
}
