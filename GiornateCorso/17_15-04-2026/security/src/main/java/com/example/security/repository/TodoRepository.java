package com.example.security.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.security.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUtenteUsernameOrderByCreatedAtDesc(String username);
    Optional<Todo> findByIdAndUtenteUsername(Long id, String username);
}
