package com.leandro.dscommerce.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.leandro.dscommerce.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    @Override
    Page<User> findAll(Pageable pageable);
    
    User findByEmail(String email);
    
   
}
