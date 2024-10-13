package com.project.footfusionbackend.service;

import com.project.footfusionbackend.model.*;
import com.project.footfusionbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Brand addBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Inventory> addInventory(List<Inventory> inventories) {

        List<Inventory> savedSkuList = new ArrayList<>();
        inventories.forEach(sku -> {
            Inventory savedSku = inventoryRepository.save(sku);
            savedSkuList.add(savedSku);
        });

        return savedSkuList;
    }

    public Inventory updateInventory(Inventory sku) {
        return inventoryRepository.save(sku);
    }
}
