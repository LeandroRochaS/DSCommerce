package com.leandro.dscommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.leandro.dscommerce.DTO.ProductDTO;
import com.leandro.dscommerce.Entity.Product;
import com.leandro.dscommerce.Service.ProductService;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping
    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        return productService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {
        ProductDTO productDTO = productService.findById(id).getBody();

        if (productDTO == null) {
            // Se o produto não for encontrado, retorna o status 204
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }

        return productDTO;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductDTO form){
        if (form.isIncomplete()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("incomplete information");
        }

        Product product = productService.save(form);

        if (product != null) {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(product.getId()).toUri();
            return ResponseEntity.created(uri).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Failed to create product");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            if(productService.findById(id) != null) {
                productService.delete(id);
                return ResponseEntity.noContent().build();
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID not found");
            }

        } catch (Exception e){
            throw new RuntimeException(e.getMessage());        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ProductDTO form, @PathVariable Long id){
        if (form.isIncomplete()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("incomplete information");
        }

        ProductDTO product = productService.update(id, form);

        if (product!= null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Failed to update product");
        }
        
    }
}



