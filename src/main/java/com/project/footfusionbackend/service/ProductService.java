package com.project.footfusionbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.footfusionbackend.model.Brand;
import com.project.footfusionbackend.model.Category;
import com.project.footfusionbackend.model.Product;
import com.project.footfusionbackend.repository.BrandRepository;
import com.project.footfusionbackend.repository.CategoryRepository;
import com.project.footfusionbackend.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductRepository productRepository;
    
    public Category getCategoryById(Long id) {
        return categoryRepository.findByCategoryId(id);
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Brand getBrandById(Long id) {
        return brandRepository.findByBrandId(id);
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findByProductId(id);
    }
}
