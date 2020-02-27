package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class OrderJpaDaoImpl implements OrderDao{

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderJpaDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addOrder(Order order) {
        LOGGER.debug("Order JPA DAO: add order {}", order);

        entityManager.persist(order);
    }

    @Override
    public void deleteOrder(Integer orderId) {
        LOGGER.debug("Order JPA DAO: delete order {}", orderId);

        Order order = entityManager.find(Order.class, orderId);
        entityManager.remove(order);
    }

    @Override
    public Order findOrderById(Integer orderId) {
        LOGGER.debug("Order JPA DAO: find order by id {}", orderId);
        return entityManager.find(Order.class, orderId);
    }

    @Override
    public void updateOrder(Order order) {
        LOGGER.debug("Order JPA DAO: update order {}", order);

        entityManager.persist(order);
    }
}
