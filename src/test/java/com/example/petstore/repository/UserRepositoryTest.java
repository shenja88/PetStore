package com.example.petstore.repository;

import com.example.petstore.entity.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
public class UserRepositoryTest {
    private final List<User> users = new ArrayList<>();
    private final UserRepository repository;

    @Autowired
    public UserRepositoryTest(UserRepository repository) {
        this.repository = repository;
    }

    @BeforeAll
    void initUserForTests() {
        users.add(User.builder().id(1).userName("Sara").firstName("Grey").lastName("Jonson").email("fgfres32@gmail.com").password("11111Aa").telephone("324325235").status(1).build());
        users.add(User.builder().id(2).userName("John").firstName("Red").lastName("Lowson").email("sdsdfdbfs@mail.com").password("22222Aa").telephone("3262362646").status(0).build());
        users.add(User.builder().id(3).userName("Eugene").firstName("Dark").lastName("Smith").email("fdsbdfge@gmail.com").password("333333Aa").telephone("784343634").status(1).build());
        users.add(User.builder().id(4).userName("Mike").firstName("Blue").lastName("Jammerson").email("sgjgsg@gmail.com").password("44444Aa").telephone("354354326").status(0).build());
    }

    @ParameterizedTest
    @CsvSource({
            "0, Sara",
            "1, John",
            "2, Eugene",
            "3, Mike"
    })
    public void addUser_and_findByUserNameTest(int idx, String userName) {
        repository.save(users.get(idx));
        User userForCheck = repository.getByUserName(userName).get();
        assertEquals(users.get(idx).getUserName(), userForCheck.getUserName());
    }

    @Test
    public void addUsersListTest_and_EqualsLists() {
        repository.saveAll(users);
        List<User> actualListUsers = new ArrayList<>();
        for (User u : users) {
            actualListUsers.add(repository.getById(u.getId()));
        }
        users.removeAll(actualListUsers);
        boolean flag = users.isEmpty();
        assertTrue(flag);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    public void removeUser_and_isExistById_Test(int id){
        repository.save(users.get(id - 1));
        repository.delete(users.get(id - 1));
        assertFalse(repository.existsById((long) id));
    }

    @Test
    public void updateUserTest(){
        repository.save(users.get(0));
        repository.save(users.get(3));

        users.get(3).setId(1);
        repository.save(users.get(3));
        assertEquals(users.get(0), repository.getById(users.get(3).getId()));
    }
}

