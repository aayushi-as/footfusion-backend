package com.project.footfusionbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.footfusionbackend.model.OrderInfo;
import java.util.List;

@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long>{
    List<OrderInfo> findByUserUserId(Long userId);
    OrderInfo findByOrderId(Long orderId);
}
