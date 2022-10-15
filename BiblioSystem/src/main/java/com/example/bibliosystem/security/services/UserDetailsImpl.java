package com.example.bibliosystem.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.bibliosystem.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Classe d'implémentation de userDetails, utilisée pour l'authentification
 */
public class UserDetailsImpl implements UserDetails {

    private Integer id;
    private String email;
    private String nom;
    private String prenom;

    @JsonIgnore
    private String mdp;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Integer id, String email, String nom, String prenom, String mdp, List<GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.authorities = authorities;
    }

    /**
     * Redéfinition de la méthode Build de Userdetail
     * @param user Objet représentant un utilisateur
     * @return un objet représentant un utilsateur dans un contexte d'authentification
     * (= un user + son/ses rôles)
     */
    public static UserDetailsImpl build(User user) {
        //On récupère les rôles
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return new UserDetailsImpl(
                user.getUserId(),
                user.getEmail(),
                user.getNom(),
                user.getPrenom(),
                user.getMdp(),
                authorities);
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }


}
