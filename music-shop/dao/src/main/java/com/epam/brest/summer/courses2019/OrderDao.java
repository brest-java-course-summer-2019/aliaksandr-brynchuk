package com.epam.brest.summer.courses2019;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.Date;
import java.util.List;

public interface OrderDao {

    Order addOrder(Order order);

    void updateOrder(MapSqlParameterSource params);

    void deleteOrder(Integer orderId);

    Order findOrderById(Integer orderId);

    List<Order> findAllOrders();

    List<Order> findOrdersByDates(Date from, Date to);

}
