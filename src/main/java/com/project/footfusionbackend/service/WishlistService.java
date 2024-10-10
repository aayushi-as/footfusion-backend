package com.project.footfusionbackend.service;

import com.project.footfusionbackend.model.Wishlist;
import com.project.footfusionbackend.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    public Wishlist addProductToWishlist(Wishlist wishlistProduct) {
        return wishlistRepository.save(wishlistProduct);
    }

    public void removeProductFromWishlist(Wishlist wishlistProduct) {
        wishlistRepository.delete(wishlistProduct);
    }

    public List<Wishlist> getAllProductsFromWishlist(Long id) {
        return wishlistRepository.findByUserUserId(id);
    }

    public Wishlist getProductByWishlistId(Long id) {
        return wishlistRepository.findByWishlistId(id);
    }
}
