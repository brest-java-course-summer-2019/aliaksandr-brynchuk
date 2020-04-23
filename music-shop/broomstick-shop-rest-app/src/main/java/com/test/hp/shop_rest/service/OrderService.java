package com.test.hp.shop_rest.service;


import com.test.hp.shop_rest.model.Order;
import com.test.hp.shop_rest.model.OrderDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * Item com.test.hp.service interface
 */

public interface OrderService {

    /**
     * Add order
     *
     * @param order Order
     */
    void addOrder(Order order);

    /**
     * Update order
     *
     * @param order Order
     */
    void updateOrder(Order order);

    /**
     * Delete order
     *
     * @param orderId Order ID
     */
    void deleteOrder(Integer orderId);

    /**
     * Find order by ID
     *
     * @param orderId Order ID
     * @return Order
     */
    Order findOrderById(Integer orderId);

    /**
     * Find all orderDTOs
     *
     * @return OrderDTOs List
     */
    List<OrderDTO> findAllOrderDTOs();

    /**
     * Find orderDTOs by dates
     *
     * @param from Date from
     * @param to Date to
     * @return OrderDTOs list
     */
    List<OrderDTO> findOrdersByDates(LocalDate from, LocalDate to);

}
