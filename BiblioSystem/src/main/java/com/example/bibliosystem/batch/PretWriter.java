package com.example.bibliosystem.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

/**
 * Classe de logging / tracking du job de relance
 */
public class PretWriter implements ItemWriter<String> {

    public void write(List<? extends String> list) throws Exception {
        //pas besoin
    }
}