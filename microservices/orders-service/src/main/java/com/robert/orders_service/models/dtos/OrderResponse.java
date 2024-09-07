package com.robert.orders_service.models.dtos;

import java.util.List;

public record OrderResponse(Long id, String orderNumber, List<OrderItemsResponse> orderItems) {
  
}
