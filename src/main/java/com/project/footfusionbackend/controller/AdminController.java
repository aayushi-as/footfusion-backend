package com.project.footfusionbackend.controller;

import com.project.footfusionbackend.dto.ProductDto;
import com.project.footfusionbackend.dto.ProductPriceDto;
import com.project.footfusionbackend.model.*;
import com.project.footfusionbackend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/category/add")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category categoryCreated = adminService.addCategory(category);
        return new ResponseEntity<>(categoryCreated, HttpStatus.CREATED);
    }

    @GetMapping("/category/view-all")
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categoryList = adminService.getAllCategory();
        return ResponseEntity.ok(categoryList);
    }

    @PostMapping("/brand/add")
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
        Brand brandCreated = adminService.addBrand(brand);
        return new ResponseEntity<>(brandCreated, HttpStatus.CREATED);
    }

    @GetMapping("/brand/view-all")
    public ResponseEntity<List<Brand>> getAllBrand() {
        List<Brand> brandList = adminService.getAllBrand();
        return ResponseEntity.ok(brandList);
    }

    @PostMapping("/color/add")
    public ResponseEntity<Color> createColor(@RequestBody Color color) {
        Color colorCreated = adminService.addColor(color);
        return new ResponseEntity<>(colorCreated, HttpStatus.CREATED);
    }

    @GetMapping("/color/view-all")
    public ResponseEntity<List<Color>> getAllColor() {
        List<Color> colorList = adminService.getAllColor();
        return ResponseEntity.ok(colorList);
    }

    @PostMapping("/tag/add")
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
        Tag tagCreated = adminService.addTag(tag);
        return new ResponseEntity<>(tagCreated, HttpStatus.CREATED);
    }

    @GetMapping("/tag/view-all")
    public ResponseEntity<List<Tag>> getAllTag() {
        List<Tag> tagList = adminService.getAllTag();
        return ResponseEntity.ok(tagList);
    }

    @PostMapping("/product/add")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto) {

        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setMaterial(productDto.getMaterial());
        product.setRating(productDto.getRating());
        product.setWarranty(productDto.getWarranty());

        Brand brand = adminService.getBrandById(productDto.getBrandIdDto());
        product.setBrand(brand);

        Color color = adminService.getColorById(productDto.getColorIdDto());
        product.setColor(color);

        Tag tag = adminService.getTagById(productDto.getTagIdDto());
        product.setTag(tag);

        Category category = adminService.getCategoryById(productDto.getCategoryIdDto());
        product.setCategory(category);

        Product createdProduct = adminService.addProduct(product);

        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PostMapping("/product/{id}/update-price")
    public ResponseEntity<Product> updateProductPrice(@RequestBody ProductPriceDto productPriceDto, @PathVariable Long id) {
        Product product = adminService.getProductById(id);
        product.setPrice(productPriceDto.getPrice());

        Product updatedProduct = adminService.updateProduct(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @PostMapping("product/{id}/sku/add")
    public ResponseEntity<List<SKU>> addProductSku(@RequestBody List<SKU> skuList, @PathVariable Long id) {
        Product product = adminService.getProductById(id);
        skuList.forEach(sku -> sku.setProduct(product));

        List<SKU> createdskuList = adminService.addSku(skuList);
        return new ResponseEntity<>(createdskuList, HttpStatus.CREATED);
    }

    @PostMapping("product/{id}/sku/{skuid}/update-stock")
    public ResponseEntity<SKU> updateProductSku(@RequestBody SKU sku, @PathVariable Long id, @PathVariable Long skuid) {
        Product product = adminService.getProductById(id);
        sku.setProduct(product);
        sku.setSkuId(skuid);
        SKU createdsku = adminService.updateSku(sku);
        return ResponseEntity.ok(createdsku);
    }

}
