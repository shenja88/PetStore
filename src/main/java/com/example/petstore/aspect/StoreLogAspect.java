package com.example.petstore.aspect;

import com.example.petstore.entity.Order;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StoreLogAspect {
    private final Logger logger = LoggerFactory.getLogger(StoreLogAspect.class.getSimpleName());

    @Pointcut("execution(public * com.example.petstore.controller.PetStoreController.inventory(..)) && args(status)")
    public void getOrdersByStatus(String status){
    }

    @Pointcut("execution(public * com.example.petstore.controller.PetStoreController.addOrder(..)) && args(order)")
    public void saveOrder(Order order){
    }

    @Pointcut("execution(public * com.example.petstore.controller.PetStoreController.getOrder(..)) && args(id)")
    public void getOrderById(long id){
    }

    @Pointcut("execution(public * com.example.petstore.controller.PetStoreController.remove(..)) && args(id)")
    public void remove(long id){
    }

    @After(value = "getOrdersByStatus(status)", argNames = "status")
    public void getAllOrdersByStatus(String status){
        logger.info("Request to orders with status - {}", status);
    }

    @After(value = "saveOrder(order)", argNames = "order")
    public void addOrder(Order order){
        logger.info("Save new order for pet id - {}", order.getPetId());
    }

    @After(value = "getOrderById(id)", argNames = "id")
    public void getById(long id){
        logger.info("Request to order with id - {}", id);
    }

    @After(value = "remove(id)", argNames = "id")
    public void deleteOrder(long id){
        logger.info("Delete order with id - {}", id);
    }
}
