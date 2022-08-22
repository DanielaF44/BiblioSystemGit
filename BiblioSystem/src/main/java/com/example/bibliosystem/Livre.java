package com.example.bibliosystem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *

 */
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

    @ManyToOne
    @JoinColumn(name ="langue")
    private Langue langue;

    @ManyToMany
    @JoinTable
    private List<Auteur> auteurs = new ArrayList<>();

    @ManyToMany
    @JoinTable
    private List<Genre> genres = new ArrayList<>();

    //@ManyToOne
    //@JoinColumn(name ="exemplaires")
    //private List<Exemplaire> exemplaires = new ArrayList<>();

    public Livre(String isbn, String titre, Date datePublication) {
        this.isbn = isbn;
        this.titre = titre;
        this.datePublication = datePublication;
    }

    public Livre() {
    }

    public String getIsbn() {
        return isbn;
    }


    public String getTitre() {
        return titre;
    }


    public Date getDatePublication() {
        return datePublication;
    }

}
