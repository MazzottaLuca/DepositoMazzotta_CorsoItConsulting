package com.example.gestionalepizzeria.service;

import com.example.gestionalepizzeria.dto.OrderDTO;
import com.example.gestionalepizzeria.dto.OrderItemDTO;
import com.example.gestionalepizzeria.entity.*;
import com.example.gestionalepizzeria.repository.OrderRepository;
import com.example.gestionalepizzeria.repository.OrderItemRepository;
import com.example.gestionalepizzeria.repository.UserRepository;
import com.example.gestionalepizzeria.repository.MenuItemRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final MenuItemRepository menuItemRepository;
    
    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository,
                        UserRepository userRepository, MenuItemRepository menuItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
        this.menuItemRepository = menuItemRepository;
    }
    
    public OrderDTO getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
    
    public List<OrderDTO> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public OrderDTO createOrder(Long userId, List<OrderItemDTO> items) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        
        BigDecimal totalPrice = BigDecimal.ZERO;
        
        for (OrderItemDTO itemDTO : items) {
            MenuItem menuItem = menuItemRepository.findById(itemDTO.getMenuItemId())
                    .orElseThrow(() -> new RuntimeException("Menu item not found"));
            
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setPriceAtOrder(menuItem.getPrice());
            
            order.getItems().add(orderItem);
            
            BigDecimal itemTotal = menuItem.getPrice().multiply(new BigDecimal(itemDTO.getQuantity()));
            totalPrice = totalPrice.add(itemTotal);
        }
        
        order.setTotalPrice(totalPrice);
        Order saved = orderRepository.save(order);
        return convertToDTO(saved);
    }
    
    private OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUserId(order.getUser().getId());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setOrderDate(order.getOrderDate());
        
        List<OrderItemDTO> items = order.getItems().stream()
                .map(item -> new OrderItemDTO(
                        item.getId(),
                        item.getMenuItem().getId(),
                        item.getMenuItem().getName(),
                        item.getQuantity(),
                        item.getPriceAtOrder()
                ))
                .collect(Collectors.toList());
        orderDTO.setItems(items);
        
        return orderDTO;
    }
}
