package com.robert.inventory_service.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.robert.inventory_service.models.Inventory;
import com.robert.inventory_service.models.dtos.BaseResponse;
import com.robert.inventory_service.models.dtos.OrderItemRequest;
import com.robert.inventory_service.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {   
    
    private final InventoryRepository inventoryRepository;

    public boolean isInStock(String sku){ 
        var inventory = inventoryRepository.findBySku(sku);

        return  inventory.filter(value->value.getQuantity() > 0).isPresent();//"isPresent" - >retornarmos true o false si exiten el stock filtramos por cantidad
    }

    public BaseResponse areInStock(List<OrderItemRequest> orderItems){

        var errorList = new ArrayList<String>();

        List<String> skus = orderItems.stream().map(OrderItemRequest :: getSku).toList();

        List<Inventory> inventoryList = inventoryRepository.findBySkuIn(skus);

        orderItems.forEach(orderItem -> {
            var inventory = inventoryList.stream().filter(value -> value.getSku().equals(orderItem.getSku())).findFirst();

            if(inventory.isEmpty()){
                errorList.add("Product with sku" + orderItem.getSku() + "order no exist");
            }else if(inventory.get().getQuantity() < orderItem.getQuantity()){
                errorList.add("Product with sku" + orderItem.getSku() + "has insufficient quantity");

            }
        });

        return errorList.size() > 0 ? 
            new BaseResponse(errorList.toArray(new String[0])) : new BaseResponse(null); 

    }

}
 