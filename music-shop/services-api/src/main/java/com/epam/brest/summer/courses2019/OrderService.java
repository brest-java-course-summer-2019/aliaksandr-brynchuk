package com.epam.brest.summer.courses2019;


import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    Order addOrder(Order order);

    void updateOrder(Order order);

    void updateOrderItems(Order order);

    void deleteOrder(Integer orderId);

    Order findOrderById(Integer orderId);

    List<OrderDTO> findAllOrderDTOs();

    List<OrderDTO> findOrdersByDates(LocalDate from, LocalDate to);

}
