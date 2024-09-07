package com.robert.orders_service.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.robert.orders_service.models.dtos.BaseResponse;
import com.robert.orders_service.models.dtos.OrderItemRequest;
import com.robert.orders_service.models.dtos.OrderItemsResponse;
import com.robert.orders_service.models.dtos.OrderRequest;
import com.robert.orders_service.models.dtos.OrderResponse;
import com.robert.orders_service.models.entities.Order;
import com.robert.orders_service.models.entities.OrderItems;
import com.robert.orders_service.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void placeOrder (OrderRequest orderRequest){ // Relizando pedido

        BaseResponse result= this.webClientBuilder.build()
                .post()
                .uri("http://localhost:8080/api/inventory/in_stock")
                .bodyValue(orderRequest.getOrderItems())
                .retrieve() //para recuperar la respuesta del servidor
                .bodyToMono(BaseResponse.class) //bodyToMono(String.class) para convertir el cuerpo de la respuesta en un Mono de tipo String.
                .block();

        if (result != null && !result.hasErrors()){

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderItems(orderRequest.getOrderItems().stream()
                .map(ordenItemRequest -> mapOrderItemRequestToOrderItem(ordenItemRequest, order))
                .toList());
        this.orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Some of the products are not in stock");
        }
    }

    private OrderItems mapOrderItemRequestToOrderItem (OrderItemRequest orderItemRequest, Order order){
        return OrderItems.builder()
                .id(orderItemRequest.getId())
                .sku(orderItemRequest.getSku())
                .price(orderItemRequest.getPrice())
                .quantity(orderItemRequest.getQuantity())
                .order(order)
                .build();
    }

    public List<OrderResponse> getAllOrders(){
        List<Order> orders = this.orderRepository.findAll();
        return orders.stream().map(this::mapToOrderResponse).toList();
    }

    private OrderResponse mapToOrderResponse(Order order){
        return new OrderResponse(
            order.getId(), 
            order.getOrderNumber(),
            order.getOrderItems().stream().map(this::mapToOrderItemRequest).toList());
    }

    private OrderItemsResponse mapToOrderItemRequest(OrderItems orderItems){
        return new OrderItemsResponse(orderItems.getId(),orderItems.getSku(),orderItems.getPrice(),orderItems.getQuantity());
    }

    
}
