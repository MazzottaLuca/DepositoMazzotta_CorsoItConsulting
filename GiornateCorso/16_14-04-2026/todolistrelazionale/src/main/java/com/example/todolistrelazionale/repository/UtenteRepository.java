package com.example.todolistrelazionale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todolistrelazionale.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
    
}
