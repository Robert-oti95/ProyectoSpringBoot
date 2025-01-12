package com.robert.orders_service.models.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "orders")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Order {

    public Order() {
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItems> OrderItems;

    
}
