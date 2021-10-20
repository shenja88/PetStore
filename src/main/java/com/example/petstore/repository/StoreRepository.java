package com.example.petstore.repository;

import com.example.petstore.entity.Order;
import com.example.petstore.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Order, Long> {

    List<Order> getAllByStatus(OrderStatus status);

}
