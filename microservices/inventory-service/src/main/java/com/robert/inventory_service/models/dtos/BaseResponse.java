package com.robert.inventory_service.models.dtos;

public record BaseResponse(String[] errorMessages) {
    
    public boolean hasErrors(){
        return errorMessages != null && errorMessages.length > 0;
    }
}
