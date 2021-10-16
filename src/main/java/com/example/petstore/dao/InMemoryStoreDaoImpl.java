package com.example.petstore.dao;

import com.example.petstore.entity.Order;
import com.example.petstore.entity.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class InMemoryStoreDaoImpl implements StoreDao {
    private final List<Order> orders = new ArrayList<>();

    @Override
    public List<Order> getByOrderStatus(OrderStatus status) {
        return orders
                .stream()
                .filter(order -> order.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    @Override
    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public Optional<Order> getOrderById(long orderId) {
        return orders.stream().filter(ord -> ord.getId() == orderId).findFirst();
    }

    @Override
    public void deleteById(long orderId) {
        orders.removeIf(ord -> ord.getId() == orderId);
    }

    @Override
    public boolean isExistById(long orderId) {
        return orders.stream().anyMatch(ord -> ord.getId() == orderId);
    }
}
