package com.leandro.dscommerce.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandro.dscommerce.Entity.Order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	Optional<Order> findById(Long id);

}
