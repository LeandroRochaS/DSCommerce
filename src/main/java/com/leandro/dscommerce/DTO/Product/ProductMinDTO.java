package com.leandro.dscommerce.DTO.Product;

import com.leandro.dscommerce.Entity.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductMinDTO {


	private String name;


	private Double price;

	private String imgUrl;

	public ProductMinDTO(Product product) {
		this.name = product.getName();
		this.price = product.getPrice();
		this.imgUrl = product.getImgUrl();
	}

	public ProductMinDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public void copyDTOtoEntity(Product entity) {
		entity.setName(this.name);
		entity.setImgUrl(this.imgUrl);
		entity.setPrice(this.price);
		// Copie outros campos, se necessário
	}

}
