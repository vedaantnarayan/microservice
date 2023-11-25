package com.vedant.user.service.external.services;

import com.vedant.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("ratings/users/{userId}")
    List<Rating> getRatingsByUserId(@PathVariable Integer userId);
}
