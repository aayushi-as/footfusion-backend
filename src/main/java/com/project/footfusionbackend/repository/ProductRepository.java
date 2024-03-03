package com.project.footfusionbackend.repository;

import com.project.footfusionbackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByProductId(Long id);
}
