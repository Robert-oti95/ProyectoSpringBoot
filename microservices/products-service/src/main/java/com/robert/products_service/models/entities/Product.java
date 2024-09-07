package com.robert.products_service.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "producto")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Product {
    

    public Product() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String sku;
    private String name;
    private String descripcion;
    private Double price;
    private Boolean status;

    @Override
    public String toString(){
        return "Product {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", descripcion='" + descripcion + '\'' +
        '}';
    }

    
}

