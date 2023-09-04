package com.leandro.dscommerce.Service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leandro.dscommerce.DTO.CategoryDTO;
import com.leandro.dscommerce.DTO.Product.ProductDTO;
import com.leandro.dscommerce.DTO.Product.ProductMinDTO;
import com.leandro.dscommerce.Entity.Category;
import com.leandro.dscommerce.Entity.Product;
import com.leandro.dscommerce.Repository.CategoryRepository;
import com.leandro.dscommerce.Repository.ProductRepository;
import com.leandro.dscommerce.Service.Exceptions.DataBaseException;
import com.leandro.dscommerce.Service.Exceptions.ResourceNotFoundException;

@Service
public class ProductService {
    
	@Autowired
    private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

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
           throw new DataBaseException(
                    "Este produto está sendo referenciado por outras entidades e não pode ser excluído.");
        }
    }

    public Page<ProductMinDTO> findAll(String name, Pageable pageable) {
        Page<Product> result = productRepository.searchByName(name, pageable);
        return result.map(x -> new ProductMinDTO(x));
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
    
    @Transactional
    public Product save(ProductDTO productDTO) {
        try {
            Product product = new Product();
            copyDTOtoEntity(productDTO, product);
            return productRepository.save(product);
        } catch (Exception e) {

            e.printStackTrace();
            return null; 
        }
    }

    public ProductDTO update(Long id, ProductDTO productDTO) {
        try {
            Product entity = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
            entity = productRepository.save(copyDTOtoEntity(productDTO, entity));
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        } catch (ResourceNotFoundException e) {
            // Trate a exceção de tipo de mídia não aceitável aqui
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    private Product copyDTOtoEntity(ProductDTO productDTO, Product entity) {
        entity.setName(productDTO.getName());
        entity.setDescription(productDTO.getDescription());
        entity.setImgUrl(productDTO.getImgUrl());
        entity.setPrice(productDTO.getPrice());
        entity.getCategories().clear();
        for(CategoryDTO catDto: productDTO.getCategories()) {
        	entity.getCategories().add(new Category(catDto));
        } 
        return entity;
    }

}
