package com.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.security.service.UtenteService;

@Controller
public class PublicController {

    private final UtenteService utenteService;

    // Costruttore per iniettare il service
    public PublicController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    @GetMapping("/")
    public String index() { 
        return "redirect:/login"; 
    }

    @GetMapping("/login")
    public String login() { 
        return "login"; 
    }

    // QUESTO MANCAVA: Mostra la pagina register.html
    @GetMapping("/register")
    public String register() { 
        return "register"; 
    }

    // GESTISCE L'INVIO DEL FORM DI REGISTRAZIONE
    @PostMapping("/register")
    public String doRegister(@RequestParam String username, 
                             @RequestParam String password, 
                             Model model) {
        try {
            utenteService.registraUtente(username, password, "USER");
            // Se tutto va bene, torna al login con un segnale di successo
            return "redirect:/login?success";
        } catch (RuntimeException e) {
            // Se l'username esiste già, ricarica la pagina con l'errore
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/home")
    public String home() { 
        return "home"; 
    }
}