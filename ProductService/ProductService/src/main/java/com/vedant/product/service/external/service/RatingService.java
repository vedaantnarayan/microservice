package com.vedant.product.service.external.service;

import com.vedant.product.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
    @GetMapping("/ratings/products/{productId}")
    List<Rating> getRatings(@PathVariable Integer productId);
}
