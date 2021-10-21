package com.example.petstore.service_for_srpingDB;

import com.example.petstore.entity.User;
import com.example.petstore.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class UserServTest {
    private final UserRepository repository;
    private final UserServ userServ;
    private final List<User> users = new ArrayList<>();

    @Autowired
    public UserServTest(UserRepository repository, UserServ userServ) {
        this.repository = repository;
        this.userServ = userServ;
    }

    @BeforeAll
    void initUserForTests() {
        users.add(User.builder().id(1).userName("Sara").firstName("Grey").lastName("Jonson").email("fgfres32@gmail.com").password("11111Aa").telephone("324325235").status(1).build());
        users.add(User.builder().id(2).userName("John").firstName("Red").lastName("Lowson").email("sdsdfdbfs@mail.com").password("22222Aa").telephone("3262362646").status(0).build());
        users.add(User.builder().id(3).userName("Eugene").firstName("Dark").lastName("Smith").email("fdsbdfge@gmail.com").password("333333Aa").telephone("784343634").status(1).build());
        users.add(User.builder().id(4).userName("Mike").firstName("Blue").lastName("Jammerson").email("sgjgsg@gmail.com").password("44444Aa").telephone("354354326").status(0).build());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Sara", "John", "Eugene", "Mike"})
    public void addUserList_and_getByUserNameTest(String userName) {
        userServ.addUserList(users);
        boolean flag = false;
        for (User u: users) {
            if(!u.getUserName().equals(repository.getByUserName(userName).get().getUserName())){
                flag = true;
            }
        }
        assertTrue(flag);
    }

    @Test
    public void addUser_and_getByUserNameTest() {
        User user = User.builder().id(5).userName("Jake").firstName("London").lastName("Wilson").email("aaaaa@gmail.com").password("555555Aa").telephone("55555555555").status(1).build();
        userServ.addUser(user);
        assertEquals(user, userServ.getUser(user.getUserName()).get());
    }

    @Test
    public void updateUser_and_getByUserNameTest() {
        User user = User.builder().id(1).userName("Ron").firstName("Nicola").lastName("Jamm").email("erererre@gmail.com").password("555555Aa").telephone("324325235").status(0).build();
        userServ.updateUser(user, User.builder().id(user.getId()).build());
        assertFalse(userServ.getUser(user.getUserName()).isPresent());
    }

    @ParameterizedTest
    @CsvSource({
            "1, Sara",
            "2, John",
            "3, Eugene",
            "4, Mike"
    })
    public void deleteTest(long id, String name) {
        userServ.delete(id);
        assertFalse(userServ.getUser(name).isPresent());
    }
}
