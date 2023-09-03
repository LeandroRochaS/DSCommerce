package com.leandro.dscommerce.DTO;

import com.leandro.dscommerce.Entity.User;

public class UserMinDTO {
	
	private Long id;
	private String name;
	public UserMinDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public UserMinDTO(User entity) {
		id = entity.getId();
		name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
