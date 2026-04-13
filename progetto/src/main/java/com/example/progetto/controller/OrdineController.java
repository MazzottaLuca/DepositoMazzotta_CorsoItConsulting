package com.example.progetto.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.progetto.service.OrdineService;

@RestController
public class OrdineController {

    private final OrdineService ordineService;

    public OrdineController(OrdineService ordineService) {
        this.ordineService = ordineService;
    }

    @PostMapping("/ordina")
    public String ordina(@RequestBody String messaggio) {
        ordineService.creaOrdine(messaggio);
        return messaggio;
    }
}