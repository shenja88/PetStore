package com.example.petstore.repository;

import com.example.petstore.entity.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
public class StoreRepositoryTest {
    private final StoreRepository repository;
    private final List<Order> orders = new ArrayList<>();

    @Autowired
    public StoreRepositoryTest(StoreRepository repository) {
        this.repository = repository;
    }

    @BeforeAll
    public void initDB(){
        orders.add(Order.builder().id(1).petId(1).quantity(1).date(LocalDateTime.now()).status(OrderStatus.APPROVED).complete(true).build());
        orders.add(Order.builder().id(2).petId(2).quantity(11).date(LocalDateTime.now()).status(OrderStatus.PLACED).complete(false).build());
        orders.add(Order.builder().id(3).petId(1).quantity(6).date(LocalDateTime.now()).status(OrderStatus.APPROVED).complete(true).build());
        orders.add(Order.builder().id(4).petId(43).quantity(4).date(LocalDateTime.now()).status(OrderStatus.DELIVERED).complete(false).build());
        repository.saveAll(orders);
    }

    @ParameterizedTest
    @ValueSource(strings = {"APPROVED", "PLACED", "DELIVERED"})
    public void getAllByStatusTest(String status){
        List<Order> actualList = orders.stream().filter(p -> p.getStatus().toString().equals(status)).collect(Collectors.toList());
        List<Order> expectedList = repository.getAllByStatus(OrderStatus.valueOf(status));
        actualList.removeAll(expectedList);
        boolean flag = actualList.isEmpty();
        assertTrue(flag);
    }

    @Test
    public void addPet_and_updPetTest(){
        Order order = Order.builder().id(4).petId(43).quantity(4).date(LocalDateTime.now()).status(OrderStatus.PLACED).complete(true).build();
        repository.save(order);
        assertEquals(orders.get(3), repository.getById(orders.get(3).getId()));
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3, 4})
    public void getById_and_deletePetTest(long id) {
        Order order = repository.getById(id);
        repository.delete(order);
        assertFalse(repository.existsById(id));
    }
}
