package com.leandro.dscommerce.Service;

import com.leandro.dscommerce.Service.Exceptions.DataBaseException;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leandro.dscommerce.DTO.ProductDTO;
import com.leandro.dscommerce.Entity.Product;
import com.leandro.dscommerce.Repository.ProductRepository;
import com.leandro.dscommerce.Service.Exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;



    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteProductById(Long id) {
        try {
            Optional<Product> productOptional = productRepository.findById(id);

            if (productOptional.isEmpty()) {
                throw new ResourceNotFoundException("Produto com o ID " + id + " não encontrado.");
            }
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Este produto está sendo referenciado por outras entidades e não pode ser excluído.");
        }
    }




    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = productRepository.findAll(pageable);
        return result.map(x -> new ProductDTO(x));
    }

    public ProductDTO findById(Long id) {
        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

            return new ProductDTO(product);
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching product by ID", e);
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

    
    public ProductDTO update(Long id, ProductDTO productDTO) {
        try {
            Product entity = productRepository.getReferenceById(id);
            copyDTOtoEntity(productDTO, entity);
            entity = productRepository.save(entity);
            return new ProductDTO(entity);
        } catch (EntityNotFoundException | LazyInitializationException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }
    

    private void copyDTOtoEntity(ProductDTO productDTO, Product entity) {
    entity.setName(productDTO.getName());
    entity.setDescription(productDTO.getDescription());
    entity.setImgUrl(productDTO.getImgUrl());
    entity.setPrice(productDTO.getPrice());
    // Copie outros campos, se necessário
}

}
