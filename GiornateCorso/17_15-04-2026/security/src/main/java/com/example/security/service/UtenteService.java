package com.example.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.security.entity.Utente;
import com.example.security.repository.UtenteRepository;

@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;

    public UtenteService(UtenteRepository utenteRepository, PasswordEncoder passwordEncoder) {
        this.utenteRepository = utenteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Utente registraUtente(String username, String password, String ruolo) {
        // Verifica se l'utente esiste già
        if (utenteRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username già esistente");
        }

        // Crea nuovo utente
        Utente utente = new Utente();
        utente.setUsername(username);
        utente.setPassword(passwordEncoder.encode(password)); // Codifica la password
        utente.setRuolo(ruolo != null ? ruolo : "USER"); // Default a USER se non specificato

        return utenteRepository.save(utente);
    }

    public boolean usernameEsistente(String username) {
        return utenteRepository.findByUsername(username).isPresent();
    }
}