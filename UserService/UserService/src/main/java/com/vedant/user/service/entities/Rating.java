package com.vedant.user.service.entities;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private Integer ratingId;
    private Integer userId;
    private Integer productId;
    private Integer ratings;
    private String feedback;
    private Product product;

}
