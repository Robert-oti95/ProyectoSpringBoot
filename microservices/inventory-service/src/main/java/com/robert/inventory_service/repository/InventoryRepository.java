package com.robert.inventory_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robert.inventory_service.models.Inventory;

import java.util.List;
import java.util.Optional;


public interface InventoryRepository extends JpaRepository<Inventory, Long>{
    
    Optional <Inventory> findBySku(String sku); //Retornar un registro de sku cuando coincida con el cod de producto

    List<Inventory> findBySkuIn(List<String> skus);
}
