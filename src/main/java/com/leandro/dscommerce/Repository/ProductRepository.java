package com.leandro.dscommerce.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.leandro.dscommerce.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    @Override
    Page<Product> findAll(Pageable pageable);
    
    
    
    @Query("SELECT obj FROM Product obj WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<Product> searchByName(String name, Pageable pegeable);
}
