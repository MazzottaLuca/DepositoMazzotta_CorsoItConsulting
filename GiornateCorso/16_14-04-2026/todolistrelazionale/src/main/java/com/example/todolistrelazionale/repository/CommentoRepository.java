package com.example.todolistrelazionale.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todolistrelazionale.model.Commento;

public interface CommentoRepository extends JpaRepository<Commento, Long> {
List<Commento> findByTodoId(Long todoId);
}
