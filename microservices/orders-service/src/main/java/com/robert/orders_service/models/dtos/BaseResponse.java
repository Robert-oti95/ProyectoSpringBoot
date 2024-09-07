package com.robert.orders_service.models.dtos;

public record BaseResponse(String[] errorMessages) {
    
    public boolean hasErrors(){
        return errorMessages != null && errorMessages.length > 0;
    }
}
