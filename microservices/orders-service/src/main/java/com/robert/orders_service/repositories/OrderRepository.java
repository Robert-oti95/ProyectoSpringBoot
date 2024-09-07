package com.robert.orders_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robert.orders_service.models.entities.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{
    
}
