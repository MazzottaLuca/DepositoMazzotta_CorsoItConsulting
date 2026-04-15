package com.example.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.dto.AuthRequest;
import com.example.security.dto.AuthResponse;
import com.example.security.dto.RefreshTokenRequest;
import com.example.security.service.CustomUserDetailsService;
import com.example.security.service.JwtService;
import com.example.security.entity.Utente;
import com.example.security.repository.UtenteRepository;

/**
 * Controller REST per la gestione dell'autenticazione tramite JWT.
 * Espone un endpoint POST /auth/login per autenticare un utente
 * e restituire un token JWT valido.
 */
@RestController
@RequestMapping("/auth") // Tutti gli endpoint di questa classe iniziano con /auth
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    private final UtenteRepository utenteRepository;

    // Iniezione delle dipendenze tramite costruttore
    public AuthController(AuthenticationManager authManager, JwtService jwtService, CustomUserDetailsService userDetailsService, UtenteRepository utenteRepository) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.utenteRepository = utenteRepository;
    }

    /**
     * Endpoint POST /auth/login
     * Riceve username e password (AuthRequest),
     * autentica l'utente e restituisce un token JWT e refresh token (AuthResponse).
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        // Autentica l'utente usando AuthenticationManager.
        // Se le credenziali sono errate, Spring Security lancia un'eccezione 401 automaticamente.
        Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // Recupera l'utente (UserDetails) per ottenere i ruoli e firmare correttamente il JWT.
        UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());

        // Genera il token JWT a partire dai dati dell'utente
        String token = jwtService.generateToken(user);

        // Genera il refresh token
        String refreshToken = jwtService.generateRefreshToken(user);

        // Salva il refresh token nel database
        Utente utente = utenteRepository.findByUsername(request.getUsername()).orElseThrow();
        utente.setRefreshToken(refreshToken);
        utenteRepository.save(utente);

        // Restituisce i token al client come risposta JSON
        return ResponseEntity.ok(new AuthResponse(token, refreshToken));
    }

    /**
     * Endpoint POST /auth/refresh
     * Riceve un refresh token e restituisce un nuovo token JWT.
     */
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();

        // Verifica che il refresh token sia valido
        if (!jwtService.isTokenValid(refreshToken)) {
            return ResponseEntity.status(401).build();
        }

        // Estrai lo username dal refresh token
        String username = jwtService.extractUsername(refreshToken);

        // Verifica che il refresh token corrisponda a quello salvato nel database
        Utente utente = utenteRepository.findByUsername(username).orElseThrow();
        if (!refreshToken.equals(utente.getRefreshToken())) {
            return ResponseEntity.status(401).build();
        }

        // Carica i dettagli dell'utente
        UserDetails user = userDetailsService.loadUserByUsername(username);

        // Genera un nuovo token JWT
        String newToken = jwtService.generateToken(user);

        // Genera un nuovo refresh token
        String newRefreshToken = jwtService.generateRefreshToken(user);

        // Salva il nuovo refresh token nel database
        utente.setRefreshToken(newRefreshToken);
        utenteRepository.save(utente);

        // Restituisce i nuovi token
        return ResponseEntity.ok(new AuthResponse(newToken, newRefreshToken));
    }

    /**
     * Endpoint POST /auth/logout
     * Invalida il refresh token dell'utente nel database.
     */
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();

        // Verifica che il refresh token sia valido
        if (!jwtService.isTokenValid(refreshToken)) {
            return ResponseEntity.status(401).build();
        }

        // Estrai lo username dal refresh token
        String username = jwtService.extractUsername(refreshToken);

        // Trova l'utente e invalida il refresh token
        Utente utente = utenteRepository.findByUsername(username).orElse(null);
        if (utente != null && refreshToken.equals(utente.getRefreshToken())) {
            utente.setRefreshToken(null); // Invalida il refresh token
            utenteRepository.save(utente);
        }

        // Restituisce successo
        return ResponseEntity.ok().build();
    }
}