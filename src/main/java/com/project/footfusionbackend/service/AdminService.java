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
    private SkuRepository skuRepository;

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

    public List<SKU> addSku(List<SKU> skuList) {

        List<SKU> savedSkuList = new ArrayList<>();
        skuList.forEach(sku -> {
            SKU savedSku = skuRepository.save(sku);
            savedSkuList.add(savedSku);
        });

        return savedSkuList;
    }

    public SKU updateSku(SKU sku) {
        return skuRepository.save(sku);
    }
}
