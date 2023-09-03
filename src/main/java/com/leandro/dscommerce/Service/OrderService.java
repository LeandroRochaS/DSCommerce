package com.leandro.dscommerce.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandro.dscommerce.DTO.CategoryDTO;
import com.leandro.dscommerce.DTO.OrderDTO;
import com.leandro.dscommerce.DTO.Product.ProductDTO;
import com.leandro.dscommerce.Entity.Category;
import com.leandro.dscommerce.Entity.Product;
import com.leandro.dscommerce.Entity.Order.Order;
import com.leandro.dscommerce.Repository.OrderRepository;
import com.leandro.dscommerce.Service.Exceptions.ResourceNotFoundException;

@Service
public class OrderService {
    
	@Autowired
    private OrderRepository repository;

    public OrderDTO findById(Long id) {
        try {
            Order order = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));

            return new OrderDTO(order);
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching order by ID", e);
        }
    }

}
