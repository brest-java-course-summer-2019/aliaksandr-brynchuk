package com.epam.brest.summer.courses2019.services;

import com.epam.brest.summer.courses2019.ItemDao;
import com.epam.brest.summer.courses2019.Order;
import com.epam.brest.summer.courses2019.OrderDao;
import com.epam.brest.summer.courses2019.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Transactional
public class OrderServiceImpl implements OrderService {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private ItemDao itemDao;
    private OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao, ItemDao itemDao) {
        this.orderDao = orderDao;
        this.itemDao = itemDao;
    }


    @Override
    public Order addOrder(Order order) {
        LOGGER.debug("Add order:  {}", order);

        orderDao.addOrder(order);
        updateOrderItems(order);

        return order;
    }


    @Override
    public void updateOrderItems(Order order) {
        LOGGER.debug("Update order:  {}", order);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("order_id", order.getOrderId());

        order.getItemsList().
                forEach(item-> {
                    parameters.addValue("item_id", item.getItemId());
                    itemDao.insertItem(parameters);
                });
    }

    @Override
    public void updateOrder(Order order) {
        itemDao.deleteItemsList(order.getOrderId());
        updateOrderItems(order);
        orderDao.orderCost(order);
    }

    @Override
    public void updateCost(Order order) {
        orderDao.orderCost(order);
    }

    @Override
    public void deleteOrder(Integer orderId) {
        LOGGER.debug("Delete order:  {}", orderId);
       orderDao.deleteOrder(orderId);
    }

    @Override
    public List<Order> findAllOrders() {
        LOGGER.debug("Find all orders");
        return orderDao.findAllOrders();
    }

    @Override
    public Order findOrderById(Integer orderId) {
        LOGGER.debug("Find order:  {}", orderId);
        return orderDao.findOrderById(orderId);
    }

    @Override
    public List<Order> findOrdersByDates(Date from, Date to) {
        LOGGER.debug("Find orders by dates: {}", from,to);
        return orderDao.findOrdersByDates(from, to);
    }
}
