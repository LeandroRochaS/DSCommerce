package com.leandro.dscommerce.DTO;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.leandro.dscommerce.Entity.Order.Order;
import com.leandro.dscommerce.Entity.Order.OrderItem;
import com.leandro.dscommerce.Entity.Order.OrderStatus;

public class OrderDTO {
	
	private Long id;
	private Instant moment;
	private OrderStatus status;
	
	
	private UserMinDTO user;
	
	private PaymentDTO payment;
	
	@NotEmpty(message = "Deve ter pelo menos um item")
	private List<OrderItemDTO> items = new ArrayList<>();
	
	
	public OrderDTO() {}

	public OrderDTO(Long id, Instant moment, OrderStatus status, UserMinDTO user, PaymentDTO payment,
			List<OrderItemDTO> items) {
		super();
		this.id = id;
		this.moment = moment;
		this.status = status;
		this.user = user;
		this.payment = payment;
		this.items = items;
	}
	
	
	
	public OrderDTO(Order entity) {
		id = entity.getId();
		moment = entity.getMoment();
		status = entity.getStatus();
		user = new UserMinDTO(entity.getClient());
		payment =  (entity.getPayment() == null) ? null : new PaymentDTO(entity.getPayment());
		for(OrderItem item: entity.getItems()) {
			items.add(new OrderItemDTO(item));
		}
	}



	public Long getId() {
		return id;
	}

	
	public Instant getMoment() {
		return moment;
	}


	public OrderStatus getStatus() {
		return status;
	}
	
	



	public UserMinDTO getUser() {
		return user;
	}

	

	public PaymentDTO getPayment() {
		return payment;
	}

	

	public List<OrderItemDTO> getItems() {
	    return items;
	}


	public Double getTotal() {
		double sum = 0.0;
		for(OrderItemDTO item: items) {
			sum += item.getSubTotal();
		}
		return sum;
	}
	
	

}
