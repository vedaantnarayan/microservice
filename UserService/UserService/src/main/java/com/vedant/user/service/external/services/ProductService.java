package com.vedant.user.service.external.services;

import com.vedant.user.service.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.naming.Name;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductService {

    @GetMapping("/products/{productId}")
    Product getProduct(@PathVariable Integer productId);
}
