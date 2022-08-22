package com.example.bibliosystem;

import javax.persistence.*;

@Entity
@Table(name = "exemplaire")
public class Exemplaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer exemplaireId;

    @ManyToOne
    @JoinColumn(name = "isbn")
    private Livre isbn;

    public Exemplaire(Integer exemplaireId, Livre isbn) {
        this.exemplaireId = exemplaireId;
        this.isbn = isbn;
    }

    public Exemplaire(){

    }

    public Integer getExemplaireId() {
        return exemplaireId;
    }

    public Livre getIsbn() {
        return isbn;
    }

    public void setExemplaireId(Integer exemplaireId) {
        this.exemplaireId = exemplaireId;
    }

    public void setIsbn(Livre isbn) {
        this.isbn = isbn;
    }
}
