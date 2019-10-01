package com.epam.brest.summer.courses2019;


public interface OrderDao {

    Order addOrder(Order order);

    void deleteOrder(Integer orderId);

    Order findOrderById(Integer orderId);

}
