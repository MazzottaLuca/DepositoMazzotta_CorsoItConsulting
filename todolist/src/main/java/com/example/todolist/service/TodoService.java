package com.example.todolist.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.todolist.entity.StatoTask;
import com.example.todolist.entity.Todo;

@Service
public class TodoService {

    private final List<Todo> todos = new ArrayList<>();
    private Long nextId = 1L;

    public List<Todo> getTodos(String stato, String search) {
    return todos.stream()
            .filter(t -> stato == null || t.getStato().name().equalsIgnoreCase(stato))
            .filter(t -> search == null || t.getDescrizione().toLowerCase().contains(search.toLowerCase()))
            .sorted(Comparator.comparingInt(Todo::getPriorita)
                    .thenComparing(Todo::getDescrizione))
            .collect(Collectors.toList());
}

    public Todo getTodoById(Long id) {
        return todos.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Todo creaTodo(Todo todo) {
        if (todo.getDataScadenza() != null && todo.getDataScadenza().isBefore(LocalDate.now())) {
            return null; // data passata, non creare il task
        }
        todo.setId(nextId++);
        todo.setStato(StatoTask.TODO);
        todos.add(todo);
        return todo;
    }

    public List<Todo> getTodosScaduti() {
        return todos.stream()
                .filter(t -> t.getDataScadenza() != null
                        && t.getDataScadenza().isBefore(LocalDate.now())
                        && t.getStato() != StatoTask.DONE)
                .collect(Collectors.toList());
    }

    public Object aggiornaTodo(Long id, Todo todoAggiornato) {
        Todo todo = getTodoById(id);
        if (todo == null) {
            return "Task non trovato.";
        }

        StatoTask statoAttuale = todo.getStato();
        StatoTask statoNuovo = todoAggiornato.getStato();

        if (!transazioneValida(statoAttuale, statoNuovo)) {
            return "Transizione di stato non consentita: " + statoAttuale + " -> " + statoNuovo;
        }

        todo.setDescrizione(todoAggiornato.getDescrizione());
        todo.setStato(statoNuovo);
        todo.setPriorita(todoAggiornato.getPriorita());
        return todo;
    }

    public void eliminaTodo(Long id) {
        todos.removeIf(t -> t.getId().equals(id));
    }

    private boolean transazioneValida(StatoTask attuale, StatoTask nuovo) {
        return switch (attuale) {
            case TODO -> nuovo == StatoTask.IN_PROGRESS || nuovo == StatoTask.CANCELLED;
            case IN_PROGRESS -> nuovo == StatoTask.DONE || nuovo == StatoTask.CANCELLED;
            default -> false;
        };
    }
}