package com.example.todolistrelazionale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todolistrelazionale.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
List<Todo> findByUtenteId(Long utenteId);
}