package com.example.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.security.dto.RegisterRequest;
import com.example.security.service.UtenteService;

@Controller
public class PublicController {

    private final UtenteService utenteService;

    public PublicController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(Authentication auth) {
        // Se già autenticato, vai direttamente a /home
        if (auth != null && auth.isAuthenticated()) {
            return "redirect:/home";
        }
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Authentication auth) {
        if (auth != null && auth.isAuthenticated()) {
            return "redirect:/home";
        }
        return "register";
    }

    @PostMapping("/register")
    public String registraUtente(@ModelAttribute RegisterRequest request,
                                 RedirectAttributes redirectAttributes) {
        try {
            if (!request.getPassword().equals(request.getConfirmPassword())) {
                redirectAttributes.addFlashAttribute("error", "Le password non corrispondono");
                return "redirect:/register";
            }
            if (request.getPassword().length() < 6) {
                redirectAttributes.addFlashAttribute("error", "La password deve essere di almeno 6 caratteri");
                return "redirect:/register";
            }
            utenteService.registraUtente(request.getUsername(), request.getPassword(), "USER");
            redirectAttributes.addFlashAttribute("success", "Registrazione completata! Ora puoi accedere.");
            return "redirect:/login";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/register";
        }
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/public/hello")
    public String salutoPubblico() {
        return "hello";
    }
}