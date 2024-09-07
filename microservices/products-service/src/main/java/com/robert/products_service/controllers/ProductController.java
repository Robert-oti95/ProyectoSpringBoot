package com.robert.products_service.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.robert.products_service.models.dtos.ProductRequest;
import com.robert.products_service.models.dtos.ProductResponse;
import com.robert.products_service.services.ProductService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody ProductRequest productRequest){ //Como parametro necesita el producto
        this.productService.addProduct(productRequest);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List <ProductResponse>getAllProducts() {
        return this.productService.getAllProducts();
    }
    
}