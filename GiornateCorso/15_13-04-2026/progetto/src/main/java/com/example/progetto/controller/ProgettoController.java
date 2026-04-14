package com.example.progetto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.progetto.service.MessaggioService;
@RestController
public class ProgettoController {
    private final MessaggioService messaggioService;
    @Autowired
    public ProgettoController(MessaggioService messaggioService){
        this.messaggioService = messaggioService;
    }
    @GetMapping("/saluta")
    public String saluta(){
        messaggioService.saluta();
        return "saluto inviato";
    }
    
}
