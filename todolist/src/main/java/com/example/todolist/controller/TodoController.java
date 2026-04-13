package com.example.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.entity.Todo;
import com.example.todolist.service.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getTodos(
            @RequestParam(required = false) String stato,
            @RequestParam(required = false) String search) {
        return todoService.getTodos(stato, search);
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable Long id) {
        return todoService.getTodoById(id);
    }

    @GetMapping("/scaduti")
    public Object getTodosScaduti() {
        return todoService.getTodosScaduti();
    }

    @PostMapping
    public Object creaTodo(@RequestBody Todo todo) {
        Todo creato = todoService.creaTodo(todo);
        if (creato == null) {
            return "Errore: la data di scadenza non può essere nel passato.";
        }
        return creato;
    }

    @PutMapping("/{id}")
    public Object aggiornaTodo(@PathVariable Long id, @RequestBody Todo todo) {
        return todoService.aggiornaTodo(id, todo);
    }

    @DeleteMapping("/{id}")
    public String eliminaTodo(@PathVariable Long id) {
        todoService.eliminaTodo(id);
        return "Todo eliminato.";
    }
}