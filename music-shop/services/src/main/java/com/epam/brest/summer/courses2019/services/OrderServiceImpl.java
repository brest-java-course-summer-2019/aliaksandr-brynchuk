package com.epam.brest.summer.courses2019.services;

import com.epam.brest.summer.courses2019.Order;
import com.epam.brest.summer.courses2019.OrderDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private OrderDao dao;

    public OrderServiceImpl(OrderDao dao) {
        this.dao = dao;
    }


    @Override
    public Order addOrder(Order order) {
        LOGGER.debug("Add order:  {}", order);

        dao.addOrder(order);
        updateOrder(order);

        return order;
    }

    @Override
    public void updateOrder(Order order) {
        LOGGER.debug("Update order:  {}", order);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("order_id", order.getOrderId());

        order.getItemsList().
                forEach(item-> {
                    parameters.addValue("item_id", item.getItemId());
                    dao.updateOrder(parameters);
                });
    }

    @Override
    public void deleteOrder(Integer orderId) {
        LOGGER.debug("Delete order:  {}", orderId);
       dao.deleteOrder(orderId);
    }

    @Override
    public List<Order> findAllOrders() {
        LOGGER.debug("Find all orders");
        return dao.findAllOrders();
    }

    @Override
    public Order findOrderById(Integer orderId) {
        LOGGER.debug("Find order:  {}", orderId);
        return dao.findOrderById(orderId);
    }

    @Override
    public List<Order> findOrdersByDates(Date from, Date to) {
        LOGGER.debug("Find orders by dates: {}", from,to);
        return dao.findOrdersByDates(from, to);
    }
}
