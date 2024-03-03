package com.project.footfusionbackend.repository;

import com.project.footfusionbackend.model.SKU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuRepository extends JpaRepository<SKU, Long> {
}
