package com.shoponline.service;

import com.shoponline.domain.User;

import java.util.List;


public interface UserService {
    User getUser(int id);

    User getUser(String nameOrEmail);

    void addUser(User user);

    boolean deleteUser(int id);

    boolean updateUser(User user);

    List<User> getAllUser();
}
