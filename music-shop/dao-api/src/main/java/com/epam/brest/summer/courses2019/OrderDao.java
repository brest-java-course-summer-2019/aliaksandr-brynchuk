package com.epam.brest.summer.courses2019;


import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public interface OrderDao {

    Order addOrder(Order order);

    BigDecimal orderCost(Order order);

    void deleteOrder(Integer orderId);

    Order findOrderById(Integer orderId);

    List<Order> findAllOrders();

    List<Order> findOrdersByDates(Date from, Date to);

}
