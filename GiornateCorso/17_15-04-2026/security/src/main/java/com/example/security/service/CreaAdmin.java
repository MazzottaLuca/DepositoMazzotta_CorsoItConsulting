package com.example.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.security.entity.Utente;
import com.example.security.repository.UtenteRepository;

import jakarta.annotation.PostConstruct;

@Service
public class CreaAdmin {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UtenteRepository utenteRepository;

    @PostConstruct
    public void init() {
        if (utenteRepository.findByUsername("admin").isEmpty()) {
            Utente nuovo = new Utente();
            nuovo.setUsername("admin");
            nuovo.setPassword(encoder.encode("admin123"));
            nuovo.setRuolo("ADMIN");
            utenteRepository.save(nuovo);

            System.out.println(" Utente admin creato con successo");
        } else {
            System.out.println(" Utente admin già esistente");
        }
    }
}
