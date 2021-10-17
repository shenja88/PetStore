package com.example.petstore.service_for_srpingDB;

import com.example.petstore.entity.User;
import com.example.petstore.repositiry.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServ {
    private final UserRepository repository;

    public boolean addUser(User user) {
        if (!repository.existsById(user.getId())) {
            repository.save(user);
            return true;
        }
        return false;
    }

    public void addUserList(List<User> userList) {
        for (User u : userList) {
            if (!repository.existsById(u.getId())) {
            repository.save(u);
            }
        }
    }

    public boolean updateUser(User userForUpd, User newUser) {
        if(repository.existsById(userForUpd.getId())){
            newUser.setId(userForUpd.getId());
            repository.save(newUser);
            return true;
        }
        return false;
    }

    public Optional<User> getUser(String userName) {
        return repository.getByUserName(userName);
    }

    public boolean delete(long userId) {
        if(repository.existsById(userId)){
            repository.deleteById(userId);
            return true;
        }
        return false;
    }
}
