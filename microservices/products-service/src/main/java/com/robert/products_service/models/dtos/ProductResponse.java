package com.robert.products_service.models.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long id;
    private String sku;
    private String name;
    private String descripcion;
    private Double price;
    private Boolean status;

    
}