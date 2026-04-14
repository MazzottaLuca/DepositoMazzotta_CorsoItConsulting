package com.example.gestionalepizzeria.service;

import com.example.gestionalepizzeria.dto.MenuItemDTO;
import com.example.gestionalepizzeria.entity.MenuItem;
import com.example.gestionalepizzeria.repository.MenuItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuItemService {
    
    private final MenuItemRepository menuItemRepository;
    
    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }
    
    public MenuItemDTO getMenuItemById(Long id) {
        return menuItemRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));
    }
    
    public List<MenuItemDTO> getAllMenuItems() {
        return menuItemRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<MenuItemDTO> getAvailableMenuItems() {
        return menuItemRepository.findByAvailableTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public MenuItemDTO createMenuItem(MenuItemDTO menuItemDTO) {
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemDTO.getName());
        menuItem.setDescription(menuItemDTO.getDescription());
        menuItem.setPrice(menuItemDTO.getPrice());
        menuItem.setAvailable(menuItemDTO.getAvailable() != null ? menuItemDTO.getAvailable() : true);
        MenuItem saved = menuItemRepository.save(menuItem);
        return convertToDTO(saved);
    }
    
    public MenuItemDTO updateMenuItem(Long id, MenuItemDTO menuItemDTO) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));
        if (menuItemDTO.getName() != null) {
            menuItem.setName(menuItemDTO.getName());
        }
        if (menuItemDTO.getDescription() != null) {
            menuItem.setDescription(menuItemDTO.getDescription());
        }
        if (menuItemDTO.getPrice() != null) {
            menuItem.setPrice(menuItemDTO.getPrice());
        }
        if (menuItemDTO.getAvailable() != null) {
            menuItem.setAvailable(menuItemDTO.getAvailable());
        }
        MenuItem updated = menuItemRepository.save(menuItem);
        return convertToDTO(updated);
    }
    
    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }
    
    private MenuItemDTO convertToDTO(MenuItem menuItem) {
        return new MenuItemDTO(menuItem.getId(), menuItem.getName(), menuItem.getDescription(), 
                menuItem.getPrice(), menuItem.getAvailable());
    }
}
