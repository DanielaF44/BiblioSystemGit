package com.example.bibliosystem.controller;

import com.example.bibliosystem.payload.response.AuteursLusResponse;
import com.example.bibliosystem.payload.response.LivresPretesResponse;
import com.example.bibliosystem.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controleur responsable des stats de la biliothèque
 */
@CrossOrigin(origins ={"http://localhost:8081", "http://localhost:8080"})
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    DashboardService dashboard;

    /**
     * Méthode qui récupère les 10 livres les plus ou moins prêtés
     * @param ordreTri 0 = tri ascendant / 1 = tri descendant
     * @return Liste de livres (utilisation d'une classe de request et de response)
     */
    @GetMapping("/livrespretes")
    public List<LivresPretesResponse> livresPretes(@RequestParam(name="ordretri", required=false) Integer ordreTri){
        // 0 => tri ascendant ASC
        // 1 => tri descendant DESC
        if( ordreTri != null && ordreTri > 0){
            ordreTri = 1;
        }else{
            ordreTri = 0;
        }
        return dashboard.livresPretes(ordreTri);
    }

    /**
     * Méthode qui récupère les 10 auteurs les plus ou moins lus (donc empruntés)
     * @param ordreTri 0 = tri ascendant / 1 = tri descendant
     * @return Liste de livres (utilisation d'une classe de request et de response)
     */
    @GetMapping("/auteurslus")
    public List<AuteursLusResponse> auteursLus(@RequestParam(name="ordretri", required=false) Integer ordreTri){
        // 0 => tri ascendant ASC
        // 1 => tri descendant DESC
        if( ordreTri != null && ordreTri > 0){
            ordreTri = 1;
        }else{
            ordreTri = 0;
        }
        return dashboard.auteursLus(ordreTri);
    }
}
