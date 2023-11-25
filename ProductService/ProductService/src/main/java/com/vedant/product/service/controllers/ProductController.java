package com.vedant.product.service.controllers;

import com.vedant.product.service.entities.Product;
import com.vedant.product.service.services.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @PostMapping
   public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
    }

    @GetMapping
   public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductBYId(@PathVariable Integer productId){
        return ResponseEntity.ok(productService.getProductById(productId));
    }

}
