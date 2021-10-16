package com.example.petstore.dao;

import com.example.petstore.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    void createWithList(List<User> userList);

    Optional<User> getByName(String userName);

    void update(User user, String userName);

    void deleteById(long userId);

    void createUser(User user);

    boolean isExistById(long userId);

    boolean isExistByUserName(String userName);
}
