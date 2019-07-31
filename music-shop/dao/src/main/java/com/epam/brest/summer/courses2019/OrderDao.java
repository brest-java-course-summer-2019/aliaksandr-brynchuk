package com.epam.brest.summer.courses2019;

import java.sql.Date;
import java.util.List;

public interface OrderDao {

    Order addOrder(Order order);

    void updateOrder(Order order);

    void deleteOrder(Integer orderId);

    List<Order> findAllOrders();

    List<Order> findOrdersByDates(Date from, Date to);

}
