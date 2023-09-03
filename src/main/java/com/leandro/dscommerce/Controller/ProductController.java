package com.leandro.dscommerce.Controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.leandro.dscommerce.DTO.ProductDTO;
import com.leandro.dscommerce.Entity.Product;
import com.leandro.dscommerce.Service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping
    public Page<ProductDTO> getAllProducts(@RequestParam(defaultValue = "") String name  ,Pageable pageable) {
        try {
        	return productService.findAll(name, pageable);
        } 
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
            ProductDTO dto = productService.findById(id);
            return ResponseEntity.ok(dto);
        
    }
    
    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody ProductDTO form){

        Product product = productService.save(form);

        if (product != null) {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(product.getId()).toUri();
            return ResponseEntity.created(uri).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating product");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@Validated @RequestBody ProductDTO form, @PathVariable Long id) {
    ProductDTO dto = productService.update(id, form);
    return ResponseEntity.ok(dto);
    }   

}



