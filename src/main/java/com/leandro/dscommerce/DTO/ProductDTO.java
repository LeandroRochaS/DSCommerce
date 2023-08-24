package com.leandro.dscommerce.DTO;

import com.leandro.dscommerce.Entity.Product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
    
    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    public ProductDTO(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imgUrl = product.getImgUrl();
    }

    public boolean isIncomplete() {
        return this.name == null || this.name.isEmpty() ||
                this.description == null || this.description.isEmpty() ||
                this.price == null;
    }
}
