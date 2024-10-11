package com.project.footfusionbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.footfusionbackend.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
    List<Cart> findByUserUserId(Long userId);
    Cart findByCartId(Long cartId);
}
