package com.vedant.user.service.services.impl;

import com.vedant.user.service.entities.Product;
import com.vedant.user.service.entities.Rating;
import com.vedant.user.service.entities.User;
import com.vedant.user.service.exceptions.ResourceNotFoundException;
import com.vedant.user.service.external.services.ProductService;
import com.vedant.user.service.external.services.RatingService;
import com.vedant.user.service.repositories.UserRepository;
import com.vedant.user.service.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProductService productService;
    @Autowired
    private RatingService ratingService;
    @Override
    public User saveUser(User user) {
        String id = UUID.randomUUID().toString();
        user.setUniqueId(id);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        users.stream().map(user -> {
            List<Rating> ratings = ratingService.getRatingsByUserId(user.getUserId());
            user.setProductRatings(ratings);
            ratings.stream().map(rating -> {
                Product prod = productService.getProduct(rating.getProductId());
                rating.setProduct(prod);
                return rating;
            }).collect(Collectors.toList());
            return user;
        }).collect(Collectors.toList());
        return users;
    }

    @Override
    public User getUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not found with Id :" + userId));

        //
       /* Rating[] ratingArr = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + userId, Rating[].class);
        assert ratingArr != null;
        List<Rating> ratings = Arrays.stream(ratingArr).toList();*/
        List<Rating> ratings = ratingService.getRatingsByUserId(userId);
        ratings.stream().map(rating -> {
            //ResponseEntity<Product> product = restTemplate.getForEntity("http://PRODUCT-SERVICE/products/" + rating.getProductId(), Product.class);
           // Product prod = product.getBody();
            Product prod =productService.getProduct(rating.getProductId());
            rating.setProduct(prod);
            return rating;
        }).collect(Collectors.toList());
        user.setProductRatings(ratings);
        return user;
    }

    @Override
    public User updateUser(Integer userId, User user) {
        User userFromDB = getUser(userId);
        String uId = userFromDB.getUniqueId();
        BeanUtils.copyProperties(user, userFromDB);
        userFromDB.setUserId(userId);
        userFromDB.setUniqueId(uId);
        return userRepository.save(userFromDB);
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }
}
