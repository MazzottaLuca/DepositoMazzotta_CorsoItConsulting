package com.example.todolistrelazionale.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolistrelazionale.model.Commento;
import com.example.todolistrelazionale.service.CommentoService;
import com.example.todolistrelazionale.service.TodoService;
import com.example.todolistrelazionale.controller.CommentoCreateRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/commenti")
@RequiredArgsConstructor
public class CommentoController {

    private final CommentoService commentoService;
    private final TodoService todoService;

    @GetMapping
    public List<Commento> getAllCommenti() {
        return commentoService.findAll();
    }

    @GetMapping("/{id}")
    public Commento getCommentoById(@PathVariable Long id) {
        return commentoService.findById(id);
    }

    @PostMapping
    public Commento createCommento(@RequestBody CommentoCreateRequest request) {
        Commento commento = new Commento();
        commento.setTesto(request.getTesto());
        commento.setTodo(todoService.findById(request.getTodoId()));
        return commentoService.save(commento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCommento(@PathVariable Long id) {
        commentoService.delete(id);
        return ResponseEntity.ok("Commento eliminato");
    }
}
