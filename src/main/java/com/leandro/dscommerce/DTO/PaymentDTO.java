package com.leandro.dscommerce.DTO;

import java.time.Instant;

import com.leandro.dscommerce.Entity.Payment;

public class PaymentDTO {
	
	private Long id;
	private Instant moment;
	public PaymentDTO(Long id, Instant moment) {
		super();
		this.id = id;
		this.moment = moment;
	}
	public PaymentDTO(Payment entity) {
		super();
		id = entity.getId();
		moment = entity.getMoment();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Instant getMoment() {
		return moment;
	}
	public void setMoment(Instant moment) {
		this.moment = moment;
	}
	
	
	

}
