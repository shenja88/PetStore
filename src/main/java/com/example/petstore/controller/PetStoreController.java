package com.example.petstore.controller;


import com.example.petstore.entity.Order;
import com.example.petstore.service.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/store")
@AllArgsConstructor
public class PetStoreController {
    private final StoreService storeService;

    @GetMapping("/inventory")
    public ResponseEntity<List<Order>> inventory(@RequestBody String status) {
        List<Order> orders = storeService.getAllByStatus(status);
        if (orders.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @PostMapping("/order")
    public ResponseEntity<Order> addOrder(@Valid @RequestBody Order order) {
        if (storeService.addOrder(order)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable long id) {
        Optional<Order> optionalOrder = storeService.getById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            return ResponseEntity.status(HttpStatus.OK).body(order);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<Order> remove(@PathVariable long id) {
        if (storeService.delete(id)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
