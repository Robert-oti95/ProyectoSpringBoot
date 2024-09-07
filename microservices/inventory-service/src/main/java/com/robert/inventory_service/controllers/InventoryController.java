package com.robert.inventory_service.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.robert.inventory_service.models.dtos.BaseResponse;
import com.robert.inventory_service.models.dtos.OrderItemRequest;
import com.robert.inventory_service.services.InventoryService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    //Endpoint 1 :Si un producto esta en el inventario - true o false(no existe)
    @GetMapping("/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("sku") String sku) {
        return inventoryService.isInStock(sku);
    }
    

    //Endpoint 2 :Desde el msOrdenes va retornar el BaseResponse y recibe como parmetro "OrderItemRequest" - verificar en una lista orderItems

    @PostMapping("/in_stock")
    @ResponseStatus(HttpStatus.OK)

    public BaseResponse areInStock(@RequestBody List<OrderItemRequest> orderItems) {
        
        return inventoryService.areInStock(orderItems);
    }
    
    
    
}
