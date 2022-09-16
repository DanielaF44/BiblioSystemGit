package com.example.bibliosystem.service;

import com.example.bibliosystem.PretRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    @Autowired
    PretRepository pretRepository;

    private static final Logger logger = LoggerFactory.getLogger(DashboardService.class);

    public List<Object> livresPlusPretes() {
        logger.info("LivresPlusPretes");
        return pretRepository.findLivresPlusPretes();
    }
}
