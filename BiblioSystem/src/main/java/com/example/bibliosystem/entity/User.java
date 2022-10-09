package com.example.bibliosystem.entity;

import com.example.bibliosystem.entity.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(	name = "utilisateur",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
@SequenceGenerator(
        name         = "userIdSeq",
        sequenceName = "user_id_seq",
        initialValue = 10,
        allocationSize = 1
)
public class User {
    @Id
    @GeneratedValue(
            generator = "userIdSeq",
            strategy  = GenerationType.SEQUENCE
    )
    @Column(name= "id")
    private Integer userId;

    @Column(name= "nom")
    private String nom;

    @Column(name= "prenom")
    private String prenom;

    @Column(name= "email")
    private String email;

    @Column(name= "mdp")
    private String mdp;

    @ManyToMany
    @JoinTable(
            name = "utilisateur_role",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))

    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String nom, String prenom, String email, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
    }

    public User(Integer userId, String nom, String prenom, String email, String mdp) {
        this.userId = userId;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
