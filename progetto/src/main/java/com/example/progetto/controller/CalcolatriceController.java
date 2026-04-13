package com.example.progetto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.progetto.service.CalcolatriceService;

@RestController
public class CalcolatriceController {
    private final CalcolatriceService calcolatriceService;
    @Autowired
    public CalcolatriceController(CalcolatriceService calcolatriceService){
        this.calcolatriceService = calcolatriceService;
    }
    @GetMapping("/somma")
    public int somma(){
        return calcolatriceService.somma(1, 2);
    }
    
}
