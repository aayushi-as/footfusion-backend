package com.project.footfusionbackend.specification;


import org.springframework.data.jpa.domain.Specification;

import com.project.footfusionbackend.model.Brand;
import com.project.footfusionbackend.model.Category;
import com.project.footfusionbackend.model.Product;
import com.project.footfusionbackend.model.Tag;


public class ProductSpecification {
    
    public static Specification<Product> hasBrand(Brand brand) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("brand"), brand);
    }

    public static Specification<Product> isWithinPriceRange(Integer minPrice, Integer maxPrice) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
    }

    public static Specification<Product> hasTag(Tag tag) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("tag"), tag);
    }

    public static Specification<Product> hasCategory(Category category) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("category"), category);
    }
}
