package com.project.footfusionbackend.repository;

import com.project.footfusionbackend.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findByBrandId(Long id);
}
