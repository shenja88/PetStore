package com.example.petstore.aspect;

import com.example.petstore.entity.User;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class UserLogAspect {

    private final Logger logger = LoggerFactory.getLogger(UserLogAspect.class.getSimpleName());

    @Pointcut("execution(public * com.example.petstore.controller.UserController.addUser(..)) && args(user))")
    public void addUser(User user){
    }

    @Pointcut("execution(public * com.example.petstore.controller.UserController.addUsers(..)) && args(users))")
    public void addUsers(List<User> users){
    }

    @Pointcut("execution(public * com.example.petstore.controller.UserController.getByUserName(..)) && args(userName)")
    public void getByName(String userName){
    }

    @Pointcut("execution(public * com.example.petstore.controller.UserController.update(..)) && args(.., userName)")
    public void upd(String userName){
    }

    @Pointcut("execution(public * com.example.petstore.controller.UserController.deleteById(..)) && args(userId)")
    public void remove(long userId){
    }


    @After(value = "addUser(user)", argNames = "user")
    public void saveUser(User user){
        logger.info("Register new user - {}", user.getUserName());
    }

    @After(value = "addUsers(users)", argNames = "users")
    public void saveUsers(List<User> users){
        for (User u: users) {
            logger.info("Register new user - {}", u.getUserName());
        }
    }

    @After(value = "getByName(userName)", argNames = "userName")
    public void getByUserName(String userName){
        logger.info("Request user - {}", userName);
    }

    @After(value = "upd(userName)", argNames = "userName")
    public void updateUser(String userName){
        logger.info("Update user with name - {}", userName);
    }

    @After(value = "remove(userId)", argNames = "userId")
    public void delete(long userId){
        logger.info("Delete user with id - {}", userId);
    }
}
