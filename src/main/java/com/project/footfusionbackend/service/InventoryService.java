package com.project.footfusionbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.footfusionbackend.model.Inventory;
import com.project.footfusionbackend.repository.InventoryRepository;

@Service
public class InventoryService {
    
    @Autowired
    private InventoryRepository inventoryRepository;


    public boolean checkIfProductIsInStock(Long productId, String size, int quantity) {
        Inventory skuInventory = inventoryRepository.findByProductProductIdAndSize(productId, size);

        if (skuInventory.getStock() - quantity >= 0)
            return true;
        
        else
            return false;
    }
}
