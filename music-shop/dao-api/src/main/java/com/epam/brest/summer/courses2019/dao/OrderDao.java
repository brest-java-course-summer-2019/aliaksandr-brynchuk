package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Order;
import reactor.core.publisher.Mono;

/**
 *Order DAO interface
 */
public interface OrderDao {
    /**
     * add order to db
     *
     * @param order Order
     */
    Mono<Void> saveOrder(Order order);

    /**
     * Delete order from db
     *
     * @param orderId Order Id
     */
    Mono<Void> deleteByOrderId(Integer orderId);

    /**
     * Find order by id
     *
     * @param orderId Order Id
     * @return Order
     */
    Mono<Order> findByOrderId(Integer orderId);

    Mono<Void> clearItemsList(Integer orderId);

    Mono<Void> updateOrderItemsList(Order order);

}
