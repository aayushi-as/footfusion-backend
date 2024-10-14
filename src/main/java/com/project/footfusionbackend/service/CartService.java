package com.project.footfusionbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.footfusionbackend.model.Cart;
import com.project.footfusionbackend.repository.CartRepository;

@Service
public class CartService {
    
    @Autowired
    private CartRepository cartRepository;

    public Cart addProductToCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public void removeProductFromCart(Cart cartProduct) {
        cartRepository.delete(cartProduct);
    }

    public List<Cart> getAllProductsFromCart(Long userId) {
        return cartRepository.findByUserUserId(userId);
    }

    public Cart getProductByCartId(Long cartId) {
        return cartRepository.findByCartId(cartId);
    }

    public int getCartTotal(Long userId) {

        int total = 0;
        List<Cart> productList = getAllProductsFromCart(userId);

        for (Cart cartProduct : productList) {
            total += cartProduct.getQuantity() * cartProduct.getProduct().getPrice();
        }

        return total;
    }
}
