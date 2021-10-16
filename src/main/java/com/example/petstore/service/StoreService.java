package com.example.petstore.service;

import com.example.petstore.dao.StoreDao;
import com.example.petstore.entity.Order;
import com.example.petstore.entity.OrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StoreService {
    private final StoreDao storeDao;

    public List<Order> getAllByStatus(String status) {
        return storeDao.getByOrderStatus(OrderStatus.valueOf(status.toUpperCase(Locale.ENGLISH)));
    }

    public boolean addOrder(Order order) {
        if (!storeDao.isExistById(order.getId())) {
            storeDao.addOrder(order);
            return true;
        }
        return false;
    }

    public Optional<Order> getById(long orderId) {
        return storeDao.getOrderById(orderId);
    }

    public boolean delete(long orderId) {
        if (storeDao.isExistById(orderId)) {
            storeDao.deleteById(orderId);
            return true;
        }
        return false;
    }
}
