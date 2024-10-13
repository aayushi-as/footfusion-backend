package com.project.footfusionbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.footfusionbackend.model.Brand;
import com.project.footfusionbackend.model.Category;
import com.project.footfusionbackend.model.Review;
import com.project.footfusionbackend.service.ProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categoryList = productService.getAllCategory();
        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/brands")
    public ResponseEntity<List<Brand>> getAllBrands() {
        List<Brand> brandList = productService.getAllBrands();
        return ResponseEntity.ok(brandList);
    }

    @GetMapping("{pid}/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long pid) {
        List<Review> reviews = productService.getAllReviews(pid);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    
}
