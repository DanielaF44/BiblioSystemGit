package com.example.bibliosystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiblioService {
    @Autowired
    BiblioRepository BiblioRepository;

    public List<Livre> showLivre(){
        return BiblioRepository.findAll();
    }

    public void addLivre(Livre newLivre){

        BiblioRepository.save(newLivre);
    }

}
