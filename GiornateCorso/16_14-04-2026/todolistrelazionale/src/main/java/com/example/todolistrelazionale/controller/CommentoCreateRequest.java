package com.example.todolistrelazionale.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentoCreateRequest {
    private String testo;
    private Long todoId;
}