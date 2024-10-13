package com.project.footfusionbackend.repository;

import com.project.footfusionbackend.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Inventory findByProductProductIdAndSize(Long productId, String size);
}
