package com.example.bibliosystem.service;

import com.example.bibliosystem.LivresPlusPretesRepository;
import com.example.bibliosystem.payload.response.LivresPlusPretesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    @Autowired
    LivresPlusPretesRepository pretRepository;

    public List<LivresPlusPretesResponse> livresPlusPretes() {
        return pretRepository.findLivresPlusPretes();
    }
}
