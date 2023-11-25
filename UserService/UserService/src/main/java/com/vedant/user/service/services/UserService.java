package com.vedant.user.service.services;

import com.vedant.user.service.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUser();

    User getUser(Integer id);

    User updateUser(Integer id,User user);

    void deleteUser(Integer id);
}
