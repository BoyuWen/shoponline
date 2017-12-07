package com.shoponline.dao;

import com.shoponline.domain.User;

import java.util.List;

public interface UserDao {

    User getUser(int id);

    User getUser(String nameOrEmail);

    void addUser(User user);

    boolean deleteUser(int id);

    boolean updateUser(User user);

    List<User> getAllUser();
}
