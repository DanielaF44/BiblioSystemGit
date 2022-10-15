package com.example.bibliosystem.entity;

import com.example.bibliosystem.entity.Exemplaire;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pret")
public class Pret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer pret_id;

    @Column(name = "date_debut")
    private Date dateDebut;

    @Column(name = "date_fin")
    private Date dateFin;

    @Column(name = "renouvele")
    private Boolean renouvele;

    @JsonManagedReference
    @OneToOne
    @JoinColumn(name ="exemplaire_id", referencedColumnName = "id")
    private Exemplaire exemplaireId;

    @Column(name = "utilisateur_id")
    private Integer utilisateur_id;

    public Pret(){

    }

    public Integer getPret_id() {
        return pret_id;
    }

    public void setPret_id(Integer pret_id) {
        this.pret_id = pret_id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Boolean getRenouvele() {
        return renouvele;
    }

    public void setRenouvele(Boolean renouvele) {
        this.renouvele = renouvele;
    }

    public Exemplaire getExemplaireId() {
        return exemplaireId;
    }

    public void setExemplaireId(Exemplaire exemplaireId) {
        this.exemplaireId = exemplaireId;
    }

    public Integer getUtilisateur_id() { return utilisateur_id;}

    public void setUtilisateur_id(Integer utilisateur_id) { this.utilisateur_id = utilisateur_id; }


}
