package com.example.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.security.dto.TodoRequest;
import com.example.security.entity.Todo;
import com.example.security.service.TodoService;

import java.util.List;

@Controller
@RequestMapping("/todolist")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public String viewTodoList(Authentication auth, Model model) {
        List<Todo> todos = todoService.findTodosByUsername(auth.getName());
        model.addAttribute("todos", todos);
        return "todolist";
    }

    @PostMapping("/create")
    public String createTodo(Authentication auth, @ModelAttribute TodoRequest request) {
        todoService.createTodo(auth.getName(), request.getTitle(), request.getDescription());
        return "redirect:/todolist";
    }

    @PostMapping("/{id}/toggle")
    public String toggleTodo(Authentication auth, @PathVariable Long id) {
        todoService.toggleCompleted(id, auth.getName());
        return "redirect:/todolist";
    }

    @PostMapping("/{id}/delete")
    public String deleteTodo(Authentication auth, @PathVariable Long id) {
        todoService.deleteTodo(id, auth.getName());
        return "redirect:/todolist";
    }
}
