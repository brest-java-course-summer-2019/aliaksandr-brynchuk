package com.epam.brest.summer.courses2019;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public interface OrderDao {

    Order addOrder(Order order);

    BigDecimal orderCost(Order order);

    void deleteOrder(Integer orderId);

    void changeStatus(Integer orderId, String status);

    Order findOrderById(Integer orderId);

    List<Order> findAllOrders();

    List<Order> findOrdersByDates(Date from, Date to);

}
