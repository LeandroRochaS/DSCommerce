package com.leandro.dscommerce.DTO;

import com.leandro.dscommerce.Entity.Order.OrderItem;

public class OrderItemDTO {
	
	private Long productId;
	private String name;
	private Double price;
	private Integer quantity;
	public OrderItemDTO(Long productId, String name, Double price, Integer quantity) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	public OrderItemDTO(OrderItem entity) {
		super();
		productId = entity.getProduct().getId();
		name = entity.getProduct().getName();
		price =  entity.getPrice();
		quantity = entity.getQuantity();
	}
	public Long getProductId() {
		return productId;
	}
	public String getName() {
		return name;
	}
	public Double getPrice() {
		return price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	
	
	
	public Double getSubTotal() {
		return price * quantity;
	}

}
