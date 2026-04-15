package com.example.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.security.entity.Todo;
import com.example.security.entity.Utente;
import com.example.security.repository.TodoRepository;
import com.example.security.repository.UtenteRepository;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UtenteRepository utenteRepository;

    public TodoService(TodoRepository todoRepository, UtenteRepository utenteRepository) {
        this.todoRepository = todoRepository;
        this.utenteRepository = utenteRepository;
    }

    public List<Todo> findTodosByUsername(String username) {
        return todoRepository.findByUtenteUsernameOrderByCreatedAtDesc(username);
    }

    public Todo createTodo(String username, String title, String description) {
        Utente utente = utenteRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato: " + username));

        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setUtente(utente);

        return todoRepository.save(todo);
    }

    public Optional<Todo> toggleCompleted(Long id, String username) {
        Optional<Todo> todo = todoRepository.findByIdAndUtenteUsername(id, username);
        todo.ifPresent(t -> {
            t.setCompleted(!t.isCompleted());
            todoRepository.save(t);
        });
        return todo;
    }

    public void deleteTodo(Long id, String username) {
        todoRepository.findByIdAndUtenteUsername(id, username)
                .ifPresent(todoRepository::delete);
    }
}
