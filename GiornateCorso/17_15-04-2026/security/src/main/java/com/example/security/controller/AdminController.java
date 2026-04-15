package com.example.security.controller;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
@GetMapping("/pannello")
public String pannelloAdmin(Authentication auth, Model model) {
    model.addAttribute("username", auth.getName());
    return "admin/pannello";
}

@GetMapping("/users")
public String gestioneUtenti(Authentication auth, Model model) {
    model.addAttribute("username", auth.getName());
    return "admin/users";
}

@GetMapping("/logs")
public String visualizzaLogs(Authentication auth, Model model) {
    model.addAttribute("username", auth.getName());
    return "admin/logs";
}
}
