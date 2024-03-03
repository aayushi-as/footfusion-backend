package com.project.footfusionbackend.repository;

import com.project.footfusionbackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryId(Long id);
}
