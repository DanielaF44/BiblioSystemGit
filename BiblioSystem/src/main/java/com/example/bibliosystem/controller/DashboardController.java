package com.example.bibliosystem.controller;

import com.example.bibliosystem.payload.response.AuteursLusResponse;
import com.example.bibliosystem.payload.response.LivresPretesResponse;
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
