package com.vedant.rating.services.impl;

import com.vedant.rating.repositories.RatingRepository;
import com.vedant.rating.services.RatingService;
import com.vedant.rating.entities.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(Integer userId) {
        return ratingRepository.findRatingByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByProductId(Integer productId) {
        return ratingRepository.findRatingByProductId(productId);
    }
}
