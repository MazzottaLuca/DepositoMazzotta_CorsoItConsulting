package com.example.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.security.entity.Utente;
import com.example.security.repository.UtenteRepository;

import jakarta.annotation.PostConstruct;

@Service
public class CreaAdmin {
    private final UtenteRepository utenteRepository;
    private final PasswordEncoder encoder;

    public CreaAdmin(UtenteRepository utenteRepository, PasswordEncoder encoder) {
        this.utenteRepository = utenteRepository;
        this.encoder = encoder;
    }

    @PostConstruct
    public void init() {
        if (utenteRepository.findByUsername("admin").isEmpty()) {
            Utente a = new Utente();
            a.setUsername("admin");
            a.setPassword(encoder.encode("admin123"));
            a.setRuolo("ADMIN");
            utenteRepository.save(a);
        }
    }
}