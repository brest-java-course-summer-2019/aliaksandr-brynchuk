package com.epam.brest.summer.courses2019;

import java.time.LocalDate;
import java.util.List;

public interface OrderDao {

    Order addOrder(Order order);

    void deleteOrder(Integer orderId);

    Order findOrderById(Integer orderId);

    List<Order> findOrdersByDates(LocalDate from, LocalDate to);


}
