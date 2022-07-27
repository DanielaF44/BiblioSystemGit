package com.example.bibliosystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins ={"http://localhost:8081", "http://localhost:8080"})
@RestController
public class BiblioController {
    @Autowired
    BiblioService biblio;

    @GetMapping("livres")
    public List<Livre> showLivre(){
        return biblio.showLivre();

    }

    @PostMapping("livres")
    public void addLivre(@RequestBody Livre newLivre){
        biblio.addLivre(newLivre);
    }
}
