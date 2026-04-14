package com.example.progetto.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class OrdineService {

    private final NotificaService notificaService;

    @Autowired
    public OrdineService(NotificaService notificaService) {
        this.notificaService = notificaService;
    }

    public void creaOrdine(String messaggio) {
        // Logica ordine
        notificaService.inviaNotifica(messaggio);
    }
}