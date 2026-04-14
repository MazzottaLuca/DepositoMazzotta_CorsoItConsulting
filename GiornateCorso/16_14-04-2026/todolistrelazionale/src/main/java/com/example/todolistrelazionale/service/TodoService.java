package com.example.todolistrelazionale.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todolistrelazionale.model.Todo;
import com.example.todolistrelazionale.repository.TodoRepository;
import com.example.todolistrelazionale.repository.UtenteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {

private final TodoRepository todoRepository;
private final UtenteRepository utenteRepository;

public List<Todo> findAll() {
return todoRepository.findAll();
}

public List<Todo> findByUtenteId(Long utenteId) {
return todoRepository.findByUtenteId(utenteId);
}

public Todo findById(Long id) {
return todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo non trovato"));
}

public Todo save(Todo todo) {
    if (todo.getUtente() != null && todo.getUtente().getId() != null) {
        todo.setUtente(utenteRepository.getReferenceById(todo.getUtente().getId()));
    }
    return todoRepository.save(todo);
}

public void delete(Long id) {
todoRepository.deleteById(id);
}
}
