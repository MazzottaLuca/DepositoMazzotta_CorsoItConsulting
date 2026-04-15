package com.example.security.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.security.entity.Utente;
import com.example.security.repository.UtenteRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UtenteRepository utenteRepository;
    public CustomUserDetailsService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utente u = utenteRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return User.builder()
                .username(u.getUsername())
                .password(u.getPassword())
                .roles(u.getRuolo())
                .build();
    }
}