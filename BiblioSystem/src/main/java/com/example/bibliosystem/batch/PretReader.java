package com.example.bibliosystem.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * Classe de lecture des prêts expirés pour job de relance
 */
public class PretReader implements ItemReader<String>{
    //stub
    //TODO : ici le code pour recuperer les prets expirés
    private String[] stringArray = {"Zero"};

    private int index = 0;

    @Override
    public String read() throws Exception, UnexpectedInputException,
            ParseException, NonTransientResourceException {
        if (index >= stringArray.length) {
            return null;
        }

        String data = index + " " + stringArray[index];
        index++;
        System.out.println("MyCustomReader    : Reading data    : "+ data);
        return data;
    }

}