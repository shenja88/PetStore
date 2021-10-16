package com.example.petstore.dao;

import com.example.petstore.entity.Order;
import com.example.petstore.entity.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface StoreDao {

    List<Order> getByOrderStatus(OrderStatus status);

    void addOrder(Order order);

    Optional<Order> getOrderById(long orderId);

    void deleteById(long orderId);

    boolean isExistById(long orderId);
}
