package com.example.petstore.service_inMemory;

import com.example.petstore.dao.UserDao;
import com.example.petstore.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserDao userDao;

    public boolean addUser(User user) {
        if (!userDao.isExistById(user.getId())) {
            userDao.createUser(user);
            return true;
        }
        return false;
    }

    public void addUserList(List<User> userList) {
        userDao.createWithList(userList);
    }

    public boolean updateUser(User userForUpd, User newUser) {
        if (userDao.isExistByUserName(userForUpd.getUserName())) {
            userDao.update(userForUpd, newUser);
            return true;
        }
        return false;
    }

    public Optional<User> getUser(String userName) {
        return userDao.getByName(userName);
    }

    public boolean delete(long userId){
        if(userDao.isExistById(userId)){
            userDao.deleteById(userId);
            return true;
        }
        return false;
    }
}
