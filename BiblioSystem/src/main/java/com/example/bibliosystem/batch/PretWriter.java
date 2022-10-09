package com.example.bibliosystem.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

/**
 * Classe de logging / tracking du job de relance
 */
public class PretWriter implements ItemWriter<String> {

    //stub
    //TODO: ici le code pour afficher le resultat du job
    @Override
    public void write(List<? extends String> list) throws Exception {
    /*    for (String data : list) {
            System.out.println("MyCustomWriter    : Writing data    : " + data);
        }
        System.out.println("MyCustomWriter    : Writing data    : completed");

     */
    }
}