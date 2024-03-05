package com.project.footfusionbackend.controller;

import com.project.footfusionbackend.model.Product;
import com.project.footfusionbackend.model.User;
import com.project.footfusionbackend.model.Wishlist;
import com.project.footfusionbackend.service.AdminService;
import com.project.footfusionbackend.service.UserService;
import com.project.footfusionbackend.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/user")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @PostMapping("/{id}/wishlist/{pid}/add-product")
    public ResponseEntity<Wishlist> addProductToWishlist(@RequestBody Wishlist wishlistProduct, @PathVariable Long id, @PathVariable Long pid) {
        User user = userService.getUserById(id);
        wishlistProduct.setUser(user);

        Product product = adminService.getProductById(pid);
        wishlistProduct.setProduct(product);

        Wishlist addedProduct = wishlistService.addProductToWishlist(wishlistProduct);
        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/wishlist/{wid}/{pid}/remove-product")
    public ResponseEntity<String> removeProductFromWishlist(@RequestBody Wishlist wishlistProduct, @PathVariable Long id, @PathVariable Long pid) {
//        User user = userService.getUserById(id);
//        wishlistProduct.setUser(user);
//
//        Product product = adminService.getProductById(pid);
//        wishlistProduct.setProduct(product);
//
//        wishlistProduct.setWishlistId(wid);
//
//        wishlistService.removeProductFromWishlist(wishlistProduct);
        return ResponseEntity.ok("Removed product from wishlist!!");
    }

    @GetMapping("/{id}/wishlist")
    public ResponseEntity<List<Wishlist>> getAllProductsFromWishlist(@PathVariable Long id) {
        List<Wishlist> list = wishlistService.getAllProductsFromWishlist(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}/wishlist/{wid}/view")
    public ResponseEntity<Wishlist> getProductFromWishlist(@PathVariable Long id, @PathVariable Long wid) {
        Wishlist wishlistProduct = wishlistService.getProductByWishlistId(wid);
        return ResponseEntity.ok(wishlistProduct);
    }

}
