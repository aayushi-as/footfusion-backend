package com.project.footfusionbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.project.footfusionbackend.model.Cart;
import com.project.footfusionbackend.model.Product;
import com.project.footfusionbackend.model.User;
import com.project.footfusionbackend.service.CartService;
import com.project.footfusionbackend.service.InventoryService;
import com.project.footfusionbackend.service.ProductService;
import com.project.footfusionbackend.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private InventoryService inventoryService;
    
    @PostMapping("/{userid}/cart/{pid}/add")
    public ResponseEntity<?> addProductToCart(@RequestBody Cart cart, @PathVariable Long userid, @PathVariable Long pid) {
        
        if (!inventoryService.checkIfProductIsInStock(pid, cart.getSize(), cart.getQuantity())) {
            return ResponseEntity.ok("Product is currently out of stock!!");
        }

        User user = userService.getUserById(userid);
        cart.setUser(user);

        Product product = productService.getProductById(pid);
        cart.setProduct(product);

        Cart cartProduct = cartService.addProductToCart(cart);
        return new ResponseEntity<>(cartProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userid}/cart/{cid}/{pid}/delete")
    public ResponseEntity<String> removeProductFromCart(@RequestBody Cart cart, @PathVariable Long userid, @PathVariable Long cid, @PathVariable Long pid) {
        User user = userService.getUserById(userid);
        cart.setUser(user);

        Product product = productService.getProductById(pid);
        cart.setProduct(product);

        cart.setCartId(cid);

        cartService.removeProductFromCart(cart);
        return ResponseEntity.ok("Product removed successfully!!");

    }

    @GetMapping("/{userid}/cart")
    public ResponseEntity<List<Cart>> getAllProductsFromCart(@PathVariable Long userid) {
        List<Cart> list = cartService.getAllProductsFromCart(userid);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{userid}/cart/{cid}")
    public ResponseEntity<Cart> getProductFromWishlist(@PathVariable Long userid, @PathVariable Long cid) {
        Cart cartProduct = cartService.getProductByCartId(cid);
        return ResponseEntity.ok(cartProduct);
    }


    @PostMapping("{userid}/cart/{pid}/update-quantity")
    public ResponseEntity<?> updateProductQuantity(@RequestBody Cart cartProduct, @PathVariable Long userid, @PathVariable Long pid){

        if (!inventoryService.checkIfProductIsInStock(pid, cartProduct.getSize(), cartProduct.getQuantity())) {
            return ResponseEntity.ok("Product is currently out of stock!!");
        }

        User user = userService.getUserById(userid);
        cartProduct.setUser(user);

        Product product = productService.getProductById(pid);
        cartProduct.setProduct(product);

        cartService.addProductToCart(cartProduct);

        return ResponseEntity.ok(cartProduct);
    }

    
}
