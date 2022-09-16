package com.example.bibliosystem.controller;

import com.example.bibliosystem.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ={"http://localhost:8081", "http://localhost:8080"})
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    DashboardService dashboard;

    @GetMapping("/livrespluspretes")
    public List<Object> livresPlusPretes(){
        return dashboard.livresPlusPretes();
    }
}
