package com.robert.orders_service.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.robert.orders_service.models.dtos.OrderRequest;
import com.robert.orders_service.models.dtos.OrderResponse;
import com.robert.orders_service.services.OrderService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
 
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {  
        this.orderService.placeOrder(orderRequest);      
        return "Order placed successfully";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getOrders() {
        return this.orderService.getAllOrders();
    }
    
    
}
