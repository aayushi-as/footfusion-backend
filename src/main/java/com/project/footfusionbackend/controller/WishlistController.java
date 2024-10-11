package com.project.footfusionbackend.controller;

import com.project.footfusionbackend.dto.WishlistResponseDTO;
import com.project.footfusionbackend.model.Product;
import com.project.footfusionbackend.model.User;
import com.project.footfusionbackend.model.Wishlist;
import com.project.footfusionbackend.service.ProductService;
import com.project.footfusionbackend.service.UserService;
import com.project.footfusionbackend.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @PostMapping("/{id}/wishlist/{pid}/add")
    public ResponseEntity<Wishlist> addProductToWishlist(@PathVariable Long id, @PathVariable Long pid) {

        Wishlist wishlistProduct = new Wishlist();

        User user = userService.getUserById(id);
        wishlistProduct.setUser(user);

        Product product = productService.getProductById(pid);
        wishlistProduct.setProduct(product);

        Wishlist addedProduct = wishlistService.addProductToWishlist(wishlistProduct);
        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/wishlist/{wid}/{pid}/delete")
    public ResponseEntity<String> removeProductFromWishlist(@PathVariable Long id, @PathVariable Long wid, @PathVariable Long pid) {

        Wishlist wishlistProduct = new Wishlist();
        User user = userService.getUserById(id);
        wishlistProduct.setUser(user);

        Product product = productService.getProductById(pid);
        wishlistProduct.setProduct(product);

        wishlistProduct.setWishlistId(wid);

        wishlistService.removeProductFromWishlist(wishlistProduct);
        return ResponseEntity.ok("Removed product from wishlist!!");
    }

    @GetMapping("/{id}/wishlist")
    public ResponseEntity<List<WishlistResponseDTO>> getAllProductsFromWishlist(@PathVariable Long id) {
        List<Wishlist> list = wishlistService.getAllProductsFromWishlist(id);
        List<WishlistResponseDTO> responseDTO = new ArrayList<>();

        for (Wishlist item : list) {
            WishlistResponseDTO dto = new WishlistResponseDTO(item.getWishlistId(), item.getProduct().getProductId());
            responseDTO.add(dto);
        }

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}/wishlist/{wid}")
    public ResponseEntity<WishlistResponseDTO> getProductFromWishlist(@PathVariable Long id, @PathVariable Long wid) {
        Wishlist wishlistProduct = wishlistService.getProductByWishlistId(wid);
        WishlistResponseDTO wishlistResponseDTO = new WishlistResponseDTO(wishlistProduct.getWishlistId(),
                wishlistProduct.getProduct().getProductId());
        return ResponseEntity.ok(wishlistResponseDTO);
    }

}
