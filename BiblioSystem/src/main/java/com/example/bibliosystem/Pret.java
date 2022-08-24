package com.example.bibliosystem;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    //@ManyToOne
    //@JoinColumn(name ="utilisateur_id")
    //private User utilisateurId;

    @JsonManagedReference
    @OneToOne
    @JoinColumn(name ="exemplaire_id", referencedColumnName = "id")
    private Exemplaire exemplaireId;

    public Pret(Date dateDebut, Date dateFin, Boolean renouvele, /* User utilisateurId,*/ Exemplaire exemplaireId) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.renouvele = renouvele;
        //this.utilisateurId = utilisateurId;
        this.exemplaireId = exemplaireId;
    }

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

 //   public User getUtilisateurId() {
 //       return utilisateurId;
 //   }

//    public void setUtilisateurId(User utilisateurId) {
 //       this.utilisateurId = utilisateurId;
 //   }

    public Exemplaire getExemplaireId() {
        return exemplaireId;
    }

    public void setExemplaireId(Exemplaire exemplaireId) {
        this.exemplaireId = exemplaireId;
    }


}
