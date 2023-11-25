package com.vedant.rating.services;

import com.vedant.rating.entities.Rating;

import java.util.List;

public interface RatingService {
    Rating create(Rating rating);

    List<Rating> getRatings();

    List<Rating> getRatingByUserId(Integer userId);

    List<Rating> getRatingByProductId(Integer productId);
}
