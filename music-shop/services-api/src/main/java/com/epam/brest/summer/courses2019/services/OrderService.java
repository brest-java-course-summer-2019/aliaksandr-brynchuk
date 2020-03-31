package com.epam.brest.summer.courses2019.services;


import com.epam.brest.summer.courses2019.model.Order;
import com.epam.brest.summer.courses2019.model.OrderDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

/**
 * Item service interface
 */

public interface OrderService {

    /**
     * Add order
     *
     * @param order Order
     */
    Mono<Void> addOrder(Order order);

    /**
     * Update order
     *
     * @param order Order
     */
    Mono<Void> updateOrder(Order order);

    /**
     * Delete order
     *
     * @param orderId Order ID
     */
    Mono<Void> deleteOrder(Integer orderId);

    /**
     * Find order by ID
     *
     * @param orderId Order ID
     * @return Order
     */
    Mono<Order> findOrderById(Integer orderId);

    /**
     * Find all orderDTOs
     *
     * @return OrderDTOs List
     */
    Flux<OrderDTO> findAllOrderDTOs();

    /**
     * Find orderDTOs by dates
     *
     * @param from Date from
     * @param to Date to
     * @return OrderDTOs list
     */
    Flux<OrderDTO> findOrdersByDates(LocalDate from, LocalDate to);

}
