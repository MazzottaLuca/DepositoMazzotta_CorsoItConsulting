package com.example.gestionalepizzeria.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestionalepizzeria.dto.MenuItemDTO;
import com.example.gestionalepizzeria.service.MenuItemService;
import com.example.gestionalepizzeria.service.UserService;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = "*")
public class MenuItemController {
    
    private final MenuItemService menuItemService;
    private final UserService userService;
    
    public MenuItemController(MenuItemService menuItemService, UserService userService) {
        this.menuItemService = menuItemService;
        this.userService = userService;
    }
    
    @GetMapping
    public ResponseEntity<List<MenuItemDTO>> getAllMenuItems() {
        return ResponseEntity.ok(menuItemService.getAllMenuItems());
    }
    
    @GetMapping("/available")
    public ResponseEntity<List<MenuItemDTO>> getAvailableMenuItems() {
        return ResponseEntity.ok(menuItemService.getAvailableMenuItems());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MenuItemDTO> getMenuItemById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(menuItemService.getMenuItemById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<MenuItemDTO> createMenuItem(@RequestHeader("User-Id") Long userId, @RequestBody MenuItemDTO menuItemDTO) {
        try {
            var user = userService.getUserById(userId);
            if (!user.getIsAdmin()) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(menuItemService.createMenuItem(menuItemDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MenuItemDTO> updateMenuItem(@PathVariable Long id, @RequestHeader("User-Id") Long userId, @RequestBody MenuItemDTO menuItemDTO) {
        try {
            var user = userService.getUserById(userId);
            if (!user.getIsAdmin()) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            return ResponseEntity.ok(menuItemService.updateMenuItem(id, menuItemDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id, @RequestHeader("User-Id") Long userId) {
        try {
            var user = userService.getUserById(userId);
            if (!user.getIsAdmin()) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            menuItemService.deleteMenuItem(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
