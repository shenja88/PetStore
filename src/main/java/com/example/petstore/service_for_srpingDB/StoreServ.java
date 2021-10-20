package com.example.petstore.service_for_srpingDB;

import com.example.petstore.entity.Order;
import com.example.petstore.entity.OrderStatus;
import com.example.petstore.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StoreServ {
    private final StoreRepository repository;

    public List<Order> getAllByStatus(String status) {
        return repository.getAllByStatus(OrderStatus.valueOf(status.toUpperCase(Locale.ENGLISH)));
    }

    public boolean addOrder(Order order) {
        if (!repository.existsById(order.getId())) {
            repository.save(order);
            return true;
        }
        return false;
    }

    public Optional<Order> getById(long orderId) {
        return repository.findById(orderId);
    }

    public boolean delete(long orderId) {
        if (repository.existsById(orderId)) {
            repository.deleteById(orderId);
            return true;
        }
        return false;
    }
}
