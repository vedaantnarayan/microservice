package com.vedant.user.service.controllers;

import com.vedant.user.service.entities.User;
import com.vedant.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    int retryCount=1;
    @GetMapping("/{userId}")
   // @CircuitBreaker(name = "ratingProductBreaker", fallbackMethod = "ratingProductFallback")
    // @Retry(name = "ratingProductService", fallbackMethod = "ratingProductFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingProductFallback")
    public ResponseEntity<User> getUser(@PathVariable Integer userId) {
        log.info("Retry Count : {}",retryCount);
        retryCount++;
        return ResponseEntity.ok(userService.getUser(userId));
    }

    public ResponseEntity<User> ratingProductFallback(Integer userId, Exception ex) {
        log.info("Fallback is executed because service is down :", ex.getMessage());
        User user = User.builder().email("def@def.com")
                .userName("def")
                .userId(123)
                .build();
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }


    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Integer userId, @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.updateUser(userId, user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("The user has been deleted for Id :" + userId);
    }
}
