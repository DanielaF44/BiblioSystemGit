package com.example.bibliosystem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

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

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }
}
