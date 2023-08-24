package com.leandro.dscommerce.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.leandro.dscommerce.DTO.ProductDTO;
import com.leandro.dscommerce.Entity.Product;
import com.leandro.dscommerce.Repository.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;



    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = productRepository.findAll(pageable);
        return result.map(x -> new ProductDTO(x));
    }


    public ResponseEntity<ProductDTO> findById(Long id) {
        try {
            Optional<Product> productOptional = productRepository.findById(id);
            if (productOptional.isPresent()) {
                ProductDTO productDTO = new ProductDTO(productOptional.get());
                return ResponseEntity.ok(productDTO);
            } else {
                return ResponseEntity.notFound().build(); // Retorna status 404
            }
        } catch (Exception e) {
            e.printStackTrace(); // Trate ou registre a exceção adequadamente
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna status 500
        }
    }


    public Product save(ProductDTO productDTO){
        try {
            Product product = new Product();
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setImgUrl(productDTO.getImgUrl());
            product.setPrice(productDTO.getPrice());
            return productRepository.save(product);
        } catch (Exception e) {
            // Trate ou registre a exceção adequadamente
            e.printStackTrace(); // Por exemplo, imprime a exceção para depuração
            return null; // Retorne um ProductReturnDTO vazio ou lance uma exceção de erro interno.
        }
    }

    public void delete(Long id){
      productRepository.deleteById(id);
    }

    public ProductDTO update(Long id, ProductDTO productDTO){
    try {
            Optional<Product> productOptional = productRepository.findById(id);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                product.setName(productDTO.getName());
                product.setDescription(productDTO.getDescription());
                product.setImgUrl(productDTO.getImgUrl());
                product.setPrice(productDTO.getPrice());
                return new ProductDTO(productRepository.save(product));
            } else {
                return null;
            }
        } catch (Exception e) {
            // Trate ou registre a exceção adequadamente
            e.printStackTrace(); // Por exemplo, imprime a exceção para depuração
            return null; // Retorne um ProductReturnDTO vazio ou lance uma exceção de erro interno.
        }
    
}


}
