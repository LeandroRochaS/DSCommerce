package com.leandro.dscommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandro.dscommerce.Entity.Order.OrderItem;
import com.leandro.dscommerce.Entity.Order.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
    
}
