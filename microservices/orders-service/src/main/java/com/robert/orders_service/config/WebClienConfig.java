package com.robert.orders_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClienConfig {
    
    @Bean
    public WebClient.Builder webClient(){
        return WebClient.builder(); //instancia de Webclient
    }
}
