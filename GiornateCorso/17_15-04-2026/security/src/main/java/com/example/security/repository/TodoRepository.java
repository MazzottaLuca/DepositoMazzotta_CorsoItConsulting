package com.example.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security.entity.Todo;
import com.example.security.entity.Utente;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUtente(Utente utente);
}