package com.leandro.dscommerce.DTO;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.leandro.dscommerce.Entity.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {

	@NotBlank(message = "O nome não pode estar vazio")
	@Size(min = 8, max = 80)
	private String name;

	@NotEmpty(message = "A descrição não pode estar vazia")
	@Size(min = 10, message = "A descrição deve ter pelo menos 10 caracteres")
	@Column(columnDefinition = "TEXT")
	private String description;

	@Positive(message = "O preço não pode ser negativo")
	private Double price;

	@NotBlank(message = "A URL da imagem não pode estar vazia")
	private String imgUrl;

	public ProductDTO(Product product) {
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
		this.imgUrl = product.getImgUrl();
	}

	public ProductDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public boolean isIncomplete() {
		return this.name == null || this.name.isEmpty() || this.description == null || this.description.isEmpty()
				|| this.price == null;
	}

	public void copyDTOtoEntity(Product entity) {
		entity.setName(this.name);
		entity.setDescription(this.description);
		entity.setImgUrl(this.imgUrl);
		entity.setPrice(this.price);
		// Copie outros campos, se necessário
	}

}
