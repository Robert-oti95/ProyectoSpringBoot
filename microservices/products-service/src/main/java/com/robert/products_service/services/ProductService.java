package com.robert.products_service.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.robert.products_service.models.dtos.ProductRequest;
import com.robert.products_service.models.dtos.ProductResponse;
import com.robert.products_service.models.entities.Product;
import com.robert.products_service.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    
    private final ProductRepository productRepository;

    public void addProduct(ProductRequest productRequest){
        var product = Product.builder() //builder de la identidad del objeto
                .sku(productRequest.getSku())
                .name(productRequest.getName())
                .descripcion(productRequest.getDescripcion())
                .price(productRequest.getPrice())
                .status(productRequest.getStatus())
                .build();

        productRepository.save(product); //save -> CRUD Repository

        log.info("Product addeeed:{}",product);
    }

    public List<ProductResponse> getAllProducts(){
        var products = productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
            .id(product.getId())
            .sku(product.getSku())
            .name(product.getName())
            .descripcion(product.getDescripcion())
            .price(product.getPrice())
            .status(product.getStatus())
            .build();
    }
}
