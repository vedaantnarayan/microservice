package com.vedant.product.service.services.impl;

import com.vedant.product.service.entities.Product;
import com.vedant.product.service.entities.Rating;
import com.vedant.product.service.exceptions.ResourceNotFoundException;
import com.vedant.product.service.external.service.RatingService;
import com.vedant.product.service.repositories.ProductRepository;
import com.vedant.product.service.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RatingService ratingService;
    @Override
    public Product createProduct(Product product) {
        String uid = UUID.randomUUID().toString();
        product.setUniqueId(uid);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productslist =productRepository.findAll();
        productslist.stream().map(product -> {
            setProductRatings(product);
            return product;
        }).collect(Collectors.toList());
        return productslist;
    }

    private void setProductRatings(Product product) {
        List<Rating> ratingList = ratingService.getRatings(product.getProductId());
        product.setProductRatings(ratingList);
    }

    @Override
    public Product getProductById(Integer productId) {
       Product product = productRepository.findById(productId).orElseThrow(() ->new ResourceNotFoundException("Product does not found with Id : "+productId));
        setProductRatings(product);
        return product;
    }
}
