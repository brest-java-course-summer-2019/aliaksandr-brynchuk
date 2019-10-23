package com.epam.brest.summer.courses2019;


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
