package com.example.petstore.dao;

import com.example.petstore.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InMemoryUserDaoImpl implements UserDao {
    private final List<User> users = new ArrayList<>();

    @Override
    public void createWithList(List<User> userList) {
        for (User u : userList) {
            if (!users.contains(u)) {
                users.add(u);
            }
        }
    }

    @Override
    public Optional<User> getByName(String userName) {
        return users.stream().filter(u -> u.getUserName().equals(userName)).findFirst();
    }

    @Override
    public void update(User userForUpd, User newUser) {
        users.set(users.indexOf(userForUpd), newUser);
    }

    @Override
    public void deleteById(long userId) {
        users.removeIf(u -> u.getId() == userId);
    }

    @Override
    public void createUser(User user) {
        users.add(user);
    }

    @Override
    public boolean isExistById(long userId) {
        return users.stream().anyMatch(u -> u.getId() == userId);
    }

    @Override
    public boolean isExistByUserName(String userName) {
        return users.stream().anyMatch(u -> u.getUserName().equals(userName));
    }
}
