package com.leandro.dscommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandro.dscommerce.DTO.OrderDTO;
import com.leandro.dscommerce.Service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    
    @Autowired
    private OrderService service;

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getProduct(@PathVariable Long id) {
            OrderDTO dto = service.findById(id);
            return ResponseEntity.ok(dto);
        
    }
    


}



