package com.vedant.product.service.services;

import com.vedant.product.service.entities.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Integer productId);

}
