package com.leandro.dscommerce.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.leandro.dscommerce.Entity.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    @Override
    Page<Product> findAll(Pageable pageable);
}
