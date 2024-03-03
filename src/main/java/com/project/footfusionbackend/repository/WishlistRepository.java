package com.project.footfusionbackend.repository;

import com.project.footfusionbackend.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    List<Wishlist> findByUserUserId(Long id);
    Wishlist findByWishlistId(Long id);
}
