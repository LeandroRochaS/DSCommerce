package com.leandro.dscommerce.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_role")
public class Role {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String authority;
	 
	
	
	public Role(Long id, String authority) {
		super();
		this.id = id;
		this.authority = authority;
	}
	public Role() {
		super();
	}
	
	

}
