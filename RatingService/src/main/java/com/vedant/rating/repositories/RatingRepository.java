package com.vedant.rating.repositories;

import com.vedant.rating.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,Integer> {
    List<Rating> findRatingByUserId(Integer userId);
    List<Rating> findRatingByProductId(Integer productId);

}
