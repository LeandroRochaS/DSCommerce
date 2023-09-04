package com.leandro.dscommerce.Service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leandro.dscommerce.DTO.OrderDTO;
import com.leandro.dscommerce.DTO.OrderItemDTO;
import com.leandro.dscommerce.Entity.Product;
import com.leandro.dscommerce.Entity.Order.Order;
import com.leandro.dscommerce.Entity.Order.OrderItem;
import com.leandro.dscommerce.Entity.Order.OrderStatus;
import com.leandro.dscommerce.Repository.OrderItemRepository;
import com.leandro.dscommerce.Repository.OrderRepository;
import com.leandro.dscommerce.Repository.ProductRepository;
import com.leandro.dscommerce.Service.Exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository itemRepository;

	@Autowired
	private UserService userService;

	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		try {
			Order order = repository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));
			return new OrderDTO(order);
		} catch (Exception e) {
			throw new RuntimeException("Error while fetching order by ID", e);
		}
	}

	@Transactional(readOnly = true)
	public OrderDTO save(OrderDTO orderDto) {
		try {
			Order order = new Order();
			order.setMoment(Instant.now());
			;
			order.setStatus(OrderStatus.WAITING_PAYMENT);
			order.setClient(userService.authenticated());
			for (OrderItemDTO itemDto : orderDto.getItems()) {
				Product product = productRepository.getReferenceById(itemDto.getProductId());
				OrderItem item = new OrderItem(order, product, itemDto.getQuantity(), product.getPrice());
				order.getItems().add(item);
			}
			repository.save(order);
			itemRepository.saveAll(order.getItems());

			return new OrderDTO(order);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
