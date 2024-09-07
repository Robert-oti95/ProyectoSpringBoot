package com.robert.inventory_service.utils;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.robert.inventory_service.models.Inventory;
import com.robert.inventory_service.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner{

        private final InventoryRepository inventoryRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Loading Data...");
        if(inventoryRepository.findAll().size() == 0){
            inventoryRepository.saveAll(
                List.of(
                    Inventory.builder().sku("000001").quantity(10L).build(),
                    Inventory.builder().sku("000002").quantity(20L).build(), 
                    Inventory.builder().sku("000003").quantity(30L).build(),
                    Inventory.builder().sku("000004").quantity(0L).build()
                    )
            );
        }
        log.info("Data loader.");
    }
    


}
