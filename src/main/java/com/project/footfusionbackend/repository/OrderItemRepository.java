package com.project.footfusionbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.footfusionbackend.model.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
    List<OrderItem> findByOrderOrderId(Long orderId);
}
