package com.robert.products_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robert.products_service.models.entities.Product;

public interface ProductRepository extends JpaRepository <Product,Long> {   
}
