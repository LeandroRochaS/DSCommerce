package com.leandro.dscommerce.DTO;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.leandro.dscommerce.Entity.Order.Order;
import com.leandro.dscommerce.Entity.Order.OrderStatus;

public class OrderDTO {
	
	private Long id;
	private Instant moment;
	private OrderStatus status;
	
	
	private UserMinDTO user;
	
	private PaymentDTO payment;
	
	private List<OrderItemDTO> items = new ArrayList<>();
	
	

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

	

	public List<OrderItemDTO> getItem() {
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
