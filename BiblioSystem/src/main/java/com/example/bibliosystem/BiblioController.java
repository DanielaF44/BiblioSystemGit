package com.example.bibliosystem;

import com.example.bibliosystem.payload.response.MessageResponse;
import com.example.bibliosystem.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

/**
 * controleur responsable de la gestion de la bibliothèque
 */
@CrossOrigin(origins ={"http://localhost:8081", "http://localhost:8080"})
@RestController
public class BiblioController {
    @Autowired
    BiblioService biblio;

    @Autowired
    UserRepository userRepository;

    /**
     * Cette méthode du controleur permet d'envoyer la liste des livres présents en base de données dans la table "livres"
     * @return List<Livre> appelle la methode showLivre du Service qui retourne la liste des livres
     */
    @GetMapping("livres")
    public List<Livre> showLivre(){
        return biblio.showLivre();

    }
    /**
     * Cette méthode du controleur permet d'envoyer la liste des genres présents en base de données dans la table "genre"
     * @return List<Genre> appelle la methode showGenre du Service qui retourne la liste des genres
     */
    @GetMapping("genres")
    public List<Genre> showGenre(){
        return biblio.showGenre();
    }

    /**
     * Cette méthode du controleur permet d'envoyer la liste des langues présentes en base de données dans la table "langue"
     * @return List<Langue> appelle la methode showLangue du Service qui retourne la liste des langues
     */
    @GetMapping("langues")
    public List<Langue> showLangue(){
        return biblio.showLangue();
    }


    /**
     * Cette méthode du controleur permet de recupérer les critères de filtrage saisis par l'utilisateur à
     * partir du Front end. Les critères "titre", "genre", "langue" sont passés à la méthode showRecherche.
     * @param  titre  le titre du livre recherché
     * @param  genre le genre du livre recherché
     * @param langue  la langue du livre recherché
     * @return List<Livre> retourne la liste de livres filtrée
     */
    @GetMapping("livresbycriteria")
    public List<Livre> showRecherche(@RequestParam(name="titre", required=false) String titre,
                                     @RequestParam(name="genre", required=false) String genre,
                                     @RequestParam(name="langue", required=false) String langue,
                                     @RequestParam(name="auteur", required=false) String auteur) {

        String messages = "";

        if(titre != null){
            if(titre.length() > 100 || titre.contains("<") || titre.contains(">") || titre.contains("=")){
                messages = "Titre invalide";
            }
        }
        if(auteur != null){
            if(auteur.length() > 40 || auteur.contains("<") || auteur.contains(">") || auteur.contains("=")){
                messages = messages.concat(", Auteur invalide");
            }
        }

        if(!messages.equals("")){

            throw new IllegalArgumentException(messages);
        }


        return biblio.showRecherche(titre, auteur, genre, langue);
    }

    /**
     * Cette méthode du controleur permet d'ajouter un livre en BD
     * @param newLivre un livre
     */
    @PostMapping("livres")
    public void addLivre(@RequestBody Livre newLivre){
        biblio.addLivre(newLivre);
    }

    /**
     * Cette méthode du controleur permet de récupérer les prêts liés à l'utilisateur qui en fait la demande
     * Pour des raisons de sécurité on récupère les données d'identification via le SecurityContextHolder
     * On ne passe pas d'informations de l'utilisateur dans le corps de la requête
     * @return
     */
    @RolesAllowed("USER")
    @GetMapping("prets")
    public List<Pret> showPret(){
        //On récupère un objet user qui correspond à l'utilisateur à l'origine de la requête
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //On récupère l'ID du user
        Optional<User> optUser =  userRepository.findByEmail(userDetails.getUsername());
        Integer utilisateurId = optUser.map( user -> user.getUserId()).orElse(null);

        //On appelle la méthode showPret en passant l'id de l'utilisateur connecté en paramètre
        return biblio.showPret(utilisateurId);
    }

    /**
     * Cette méthode du controleur permet à un utilisateur de prolonger un prêt
     * @param pretId id du prêt à prolonger
     */
    @RolesAllowed("USER")
    @PostMapping("prets")
    public void prolongePret(@RequestParam(name="pretId") Integer pretId){
        biblio.updateDateFin(pretId);
    }

    /**
     * Cette méthode  du controleur permet d'afficher les exemplaires disponibles d'un livre
     * Axe d'amélioration : faire un showpret qui prenne en compte les dispo
     * @param isbn l'isbn d'un livre
     * @return la liste des exemplaires dispos par bibliothèque
     */
    @GetMapping("disponibilite")
    public List<Object> showDispos(@RequestParam(name="isbn") String isbn){
        return biblio.countExemplaire(isbn);
    }
}
