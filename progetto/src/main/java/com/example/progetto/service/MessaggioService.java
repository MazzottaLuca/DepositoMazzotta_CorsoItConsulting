package com.example.progetto.service;

import org.springframework.stereotype.Service;

@Service
public class MessaggioService {
    public void saluta(){
        System.out.println("Ciao dal service");
    }
}
