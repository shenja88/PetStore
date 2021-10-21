package com.example.petstore.service_for_srpingDB;

import com.example.petstore.entity.Order;
import com.example.petstore.entity.OrderStatus;
import com.example.petstore.repository.StoreRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class StoreServTest {
    private final StoreServ storeServ;
    private final StoreRepository repository;
    private final List<Order> orders = new ArrayList<>();

    @Autowired
    public StoreServTest(StoreServ storeServ, StoreRepository repository) {
        this.storeServ = storeServ;
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
    void getAllByStatusTest(String status) {
        List<Order> actualList = orders.stream().filter(p -> p.getStatus().toString().equals(status)).collect(Collectors.toList());
        List<Order> expectedList = storeServ.getAllByStatus(status);
        actualList.removeAll(expectedList);
        boolean flag = actualList.isEmpty();
        assertTrue(flag);
    }

    @Test
    void addOrder_and_getByIdTest() {
        Order order = Order.builder().id(5).petId(11).quantity(2).date(LocalDateTime.now()).status(OrderStatus.PLACED).complete(true).build();
        assertFalse(storeServ.getById(order.getId()).isPresent());
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3, 4})
    void delete_and_getByIdTest(long id) {
        storeServ.delete(id);
        assertFalse(repository.existsById(id));
    }
}