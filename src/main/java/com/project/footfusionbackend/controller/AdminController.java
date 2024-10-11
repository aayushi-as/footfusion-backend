package com.project.footfusionbackend.controller;

import com.project.footfusionbackend.dto.ProductDto;
import com.project.footfusionbackend.dto.ProductPriceDto;
import com.project.footfusionbackend.model.*;
import com.project.footfusionbackend.service.AdminService;
import com.project.footfusionbackend.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/product")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ProductService productService;

    @PostMapping("/category/add")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category categoryCreated = adminService.addCategory(category);
        return new ResponseEntity<>(categoryCreated, HttpStatus.CREATED);
    }

    

    @PostMapping("/brand/add")
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
        Brand brandCreated = adminService.addBrand(brand);
        return new ResponseEntity<>(brandCreated, HttpStatus.CREATED);
    }


    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto) {

        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setMaterial(productDto.getMaterial());
        product.setRating(productDto.getRating());
        product.setWarranty(productDto.getWarranty());

        Brand brand = productService.getBrandById(productDto.getBrandIdDto());
        product.setBrand(brand);

        product.setTag(productDto.getTag());

        Category category = productService.getCategoryById(productDto.getCategoryIdDto());
        product.setCategory(category);

        Product createdProduct = adminService.addProduct(product);

        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/update-price")
    public ResponseEntity<Product> updateProductPrice(@RequestBody ProductPriceDto productPriceDto, @PathVariable Long id) {
        Product product = productService.getProductById(id);
        product.setPrice(productPriceDto.getPrice());

        Product updatedProduct = adminService.updateProduct(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @PostMapping("/{id}/sku/add")
    public ResponseEntity<List<SKU>> addProductSku(@RequestBody List<SKU> skuList, @PathVariable Long id) {
        Product product = productService.getProductById(id);
        skuList.forEach(sku -> sku.setProduct(product));

        List<SKU> createdskuList = adminService.addSku(skuList);
        return new ResponseEntity<>(createdskuList, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/sku/{skuid}/update-stock")
    public ResponseEntity<SKU> updateProductSku(@RequestBody SKU sku, @PathVariable Long id, @PathVariable Long skuid) {
        Product product = productService.getProductById(id);
        sku.setProduct(product);
        sku.setSkuId(skuid);
        SKU createdsku = adminService.updateSku(sku);
        return ResponseEntity.ok(createdsku);
    }

}
