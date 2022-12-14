package com.example.bibliosystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

    @JsonBackReference
    @OneToOne(mappedBy = "exemplaireId")
    private Pret pretId;

    public Exemplaire(){

    }

    public Pret getPretId() {
        return pretId;
    }

    public void setPretId(Pret pretId) {
        this.pretId = pretId;
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
