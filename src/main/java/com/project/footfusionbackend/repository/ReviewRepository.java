package com.project.footfusionbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.footfusionbackend.model.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>{
    List<Review> findByProductProductId(Long productId);
}
