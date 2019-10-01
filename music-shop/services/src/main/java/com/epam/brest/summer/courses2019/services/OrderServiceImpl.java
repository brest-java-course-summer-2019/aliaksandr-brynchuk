package com.epam.brest.summer.courses2019.services;

import com.epam.brest.summer.courses2019.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;


@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private ItemDao itemDao;
    private OrderDao orderDao;
    private OrderDTODao orderDTODao;


    @Autowired
    public OrderServiceImpl(OrderDao orderDao, ItemDao itemDao, OrderDTODao orderDTODao) {
        this.orderDao = orderDao;
        this.itemDao = itemDao;
        this.orderDTODao = orderDTODao;
    }


    @Override
    public Order addOrder(Order order) {
        LOGGER.debug("Add order:  {}", order);

        order.setOrderDate(LocalDate.now());
        System.out.println("test date" + order.getOrderDate());
        orderDao.addOrder(order);
        updateOrderItems(order);
        return order;
    }


    @Override
    public void updateOrderItems(Order order) {
        LOGGER.debug("Update order:  {}", order);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("order_id", order.getOrderId());

        order.getItemsIds().
                forEach(item-> {
                    parameters.addValue("item_id", item);
                    itemDao.insertItem(parameters); });
    }

    @Override
    public void updateOrder(Order order) {
        LOGGER.debug("update order:  {}", order);

        itemDao.deleteItemsList(order.getOrderId());
        updateOrderItems(order);
    }

    @Override
    public void deleteOrder(Integer orderId) {
        LOGGER.debug("Delete order:  {}", orderId);
        orderDao.deleteOrder(orderId);
    }

    @Override
    public List<OrderDTO> findAllOrderDTOs() {
        LOGGER.debug("Find all orders");
        return orderDTODao.findAllOrderDTOs();
    }

    @Override
    public Order findOrderById(Integer orderId) {
        LOGGER.debug("Find order:  {}", orderId);


        Order orderById = orderDao.findOrderById(orderId);
        List<Item> items= itemDao.itemsListFromOrder(orderId);
        orderById.setItemsList(items);
        return orderById;
    }

    @Override
    public List<Order> findOrdersByDates(LocalDate from, LocalDate to) {
        LOGGER.debug("Find orders by dates: {}", from,to);
        return orderDao.findOrdersByDates(from, to);
    }

}
