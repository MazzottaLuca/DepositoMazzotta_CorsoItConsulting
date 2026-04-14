package com.example.gestionalepizzeria.service;

import com.example.gestionalepizzeria.dto.UserDTO;
import com.example.gestionalepizzeria.entity.User;
import com.example.gestionalepizzeria.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    public UserDTO getUserByName(String name) {
        return userRepository.findByName(name)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setIsAdmin(userDTO.getIsAdmin() != null ? userDTO.getIsAdmin() : false);
        User saved = userRepository.save(user);
        return convertToDTO(saved);
    }
    
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (userDTO.getName() != null) {
            user.setName(userDTO.getName());
        }
        if (userDTO.getIsAdmin() != null) {
            user.setIsAdmin(userDTO.getIsAdmin());
        }
        User updated = userRepository.save(user);
        return convertToDTO(updated);
    }
    
    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getIsAdmin());
    }
}
