package com.example.bibliosystem.entity;

import com.example.bibliosystem.entity.Auteur;
import com.example.bibliosystem.entity.Editeur;
import com.example.bibliosystem.entity.Genre;
import com.example.bibliosystem.entity.Langue;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "livres")
public class Livre {
    @Id
    @Column(name = "isbn")
    private String isbn;

    @Column(name = "titre")
    private String titre;

    @Column(name = "date_publication")
    private Date datePublication;

    @ManyToOne
    @JoinColumn(name ="editeur_id")
    private Editeur editeur;

    @Column(name="description")
    private String description;

    @Column(name="cover")
    private String cover;

    @ManyToOne
    @JoinColumn(name ="langue")
    private Langue langue;

    @ManyToMany
    @JoinTable(name="livre_auteur", joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "auteur_id"))
    private List<Auteur> auteurs = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="livre_genre", joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "nom"))
    private List<Genre> genres = new ArrayList<>();

    //@ManyToOne
    //@JoinColumn(name ="exemplaires")
    //private List<Exemplaire> exemplaires = new ArrayList<>();
    public Livre() {
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public Editeur getEditeur() {
        return editeur;
    }

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }

    public Langue getLangue() {
        return langue;
    }

    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    public List<Auteur> getAuteurs() {
        return auteurs;
    }

    public void setAuteurs(List<Auteur> auteurs) {
        this.auteurs = auteurs;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
