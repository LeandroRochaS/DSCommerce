package com.leandro.dscommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandro.dscommerce.Entity.Order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
