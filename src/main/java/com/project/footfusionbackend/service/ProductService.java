package com.project.footfusionbackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.project.footfusionbackend.model.Brand;
import com.project.footfusionbackend.model.Category;
import com.project.footfusionbackend.model.Product;
import com.project.footfusionbackend.model.Review;
import com.project.footfusionbackend.model.Tag;
import com.project.footfusionbackend.repository.BrandRepository;
import com.project.footfusionbackend.repository.CategoryRepository;
import com.project.footfusionbackend.repository.ProductRepository;
import com.project.footfusionbackend.repository.ReviewRepository;
import com.project.footfusionbackend.specification.ProductSpecification;

@Service
public class ProductService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReviewRepository reviewRepository;
    
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

    public List<Review> getAllReviews(Long pid) {
        return reviewRepository.findByProductProductId(pid);
    }

    public Page<Product> findProducts(Optional<Long> brandId, Optional<Long> categoryId, Optional<Integer> tag, Optional<Integer> minPrice, Optional<Integer> maxPrice, Pageable pageable) {

        Specification<Product> spec = Specification.where(null);

        if(tag.isPresent()) {
            Tag tagEnum = Tag.MEN;

            if(tag.get() == 1)
                tagEnum = Tag.WOMEN;
            
            spec = spec.and(ProductSpecification.hasTag(tagEnum));
            System.out.println("Added tag specification");
        }

        if (brandId.isPresent()) {
            Brand brand = getBrandById(brandId.get());
            spec = spec.and(ProductSpecification.hasBrand(brand));
            System.out.println("Added brand specification");
        }

        if(categoryId.isPresent()) {
            Category category = getCategoryById(categoryId.get());
            spec = spec.and(ProductSpecification.hasCategory(category));
            System.out.println("Added category specification");
        }

        if(minPrice.isPresent() && maxPrice.isPresent()) {
            spec = spec.and(ProductSpecification.isWithinPriceRange(minPrice.get(), maxPrice.get()));
            System.out.println("Added price specification");
        }
        
        return productRepository.findAll(spec, pageable);
        
    }
}
