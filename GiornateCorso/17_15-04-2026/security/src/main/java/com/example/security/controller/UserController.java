package com.example.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
@GetMapping("/info")
public String infoUtente(Authentication auth) {
return "Ciao " + auth.getName() + ", sei autenticato come USER o ADMIN.";
}

@GetMapping("/dashboard")
public String dashboard(Authentication auth) {
return "Dashboard personale di " + auth.getName() + ". Qui puoi vedere le tue statistiche e attività recenti.";
}


@GetMapping("/profile")
public String profile(Authentication auth) {
return "Profilo di " + auth.getName() + ". Qui puoi modificare le tue impostazioni.";
}
}
