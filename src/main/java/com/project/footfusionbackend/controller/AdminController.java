package com.project.footfusionbackend.controller;

import com.project.footfusionbackend.dto.ProductDto;
import com.project.footfusionbackend.dto.ProductPriceDto;
import com.project.footfusionbackend.model.*;
import com.project.footfusionbackend.service.AdminService;
import com.project.footfusionbackend.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/admin/product")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ProductService productService;

    @PostMapping("/category/add")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {
        Category categoryCreated = adminService.addCategory(category);
        return new ResponseEntity<>(categoryCreated, HttpStatus.CREATED);
    }

    

    @PostMapping("/brand/add")
    public ResponseEntity<Brand> createBrand(@Valid @RequestBody Brand brand) {
        Brand brandCreated = adminService.addBrand(brand);
        return new ResponseEntity<>(brandCreated, HttpStatus.CREATED);
    }


    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductDto productDto) {

        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setMaterial(productDto.getMaterial());
        product.setRating(productDto.getRating());
        product.setWarranty(productDto.getWarranty());
        product.setCreatedAt(LocalDateTime.now());

        Brand brand = productService.getBrandById(productDto.getBrandIdDto());
        product.setBrand(brand);

        product.setTag(productDto.getTag());

        Category category = productService.getCategoryById(productDto.getCategoryIdDto());
        product.setCategory(category);

        Product createdProduct = adminService.addProduct(product);

        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PostMapping("/{pid}/update-price")
    public ResponseEntity<Product> updateProductPrice(@Valid @RequestBody ProductPriceDto productPriceDto, @PathVariable Long pid) {
        Product product = productService.getProductById(pid);
        product.setPrice(productPriceDto.getPrice());

        Product updatedProduct = adminService.updateProduct(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @PostMapping("/{pid}/inventory/add")
    public ResponseEntity<List<Inventory>> addProductInventory(@Valid @RequestBody List<Inventory> inventoryList, @PathVariable Long pid) {
        Product product = productService.getProductById(pid);
        inventoryList.forEach(sku -> sku.setProduct(product));

        List<Inventory> createdskuList = adminService.addInventory(inventoryList);
        return new ResponseEntity<>(createdskuList, HttpStatus.CREATED);
    }

    @PostMapping("/{pid}/inventory/update-stock")
    public ResponseEntity<Inventory> updateProductInventory(@Valid @RequestBody Inventory inventory, @PathVariable Long pid) {
        Product product = productService.getProductById(pid);
        inventory.setProduct(product);
        Inventory createdsku = adminService.updateInventory(inventory);
        return ResponseEntity.ok(createdsku);
    }

}
