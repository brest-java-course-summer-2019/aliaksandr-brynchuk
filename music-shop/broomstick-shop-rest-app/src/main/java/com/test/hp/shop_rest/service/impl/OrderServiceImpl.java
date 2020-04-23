package com.test.hp.shop_rest.service.impl;


import com.test.hp.shop_rest.model.Order;
import com.test.hp.shop_rest.model.OrderDTO;
import com.test.hp.shop_rest.repository.OrderDTODao;
import com.test.hp.shop_rest.repository.OrderDao;
import com.test.hp.shop_rest.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Order Service implementation
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    /**
     * Logger
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    /**
     * Order DAO field
     */
    private OrderDao orderDao;

    /**
     * OrderDTO DAO field
     */
    private OrderDTODao orderDTODao;

    /**
     * Constructor, injection order dao, item dao, orderDTO beans
     *
     * @param orderDao Order DAO
     * @param orderDTODao OrderDTO DAO
     */
    @Autowired
    public OrderServiceImpl(OrderDao orderDao, OrderDTODao orderDTODao) {
        this.orderDao = orderDao;
        this.orderDTODao = orderDTODao;
    }

    /**
     * Add order
     * Set order date
     * add items to order
     *
     * @param order Order
     */
    @Override
    public void addOrder(Order order) {
        LOGGER.debug("Order com.test.hp.service: add order:  {}", order);

        order.setOrderDate(LocalDate.now());

        orderDao.addOrder(order);

        orderDao.updateOrderItemsList(order);
    }

    @Override
    public void updateOrder(Order order) {
        LOGGER.debug("Order com.test.hp.service: update order: {}", order);

        orderDao.clearItemsList(order.getOrderId());

        orderDao.updateOrderItemsList(order);
    }

    /**
     * Delete order
     *
     * @param orderId Order ID
     */
    @Override
    public void deleteOrder(Integer orderId) {
        LOGGER.debug("Order com.test.hp.service: delete order:  {}", orderId);

        orderDao.deleteOrder(orderId);
    }

    /**
     * Find all orderDTOs
     *
     * @return OrderDTOs List
     */
    @Override
    public List<OrderDTO> findAllOrderDTOs() {
        LOGGER.debug("Order com.test.hp.service: find all orders");

        return orderDTODao.findAll();
    }

    /**
     * Find order by ID
     *
     * @param orderId Order ID
     * @return Order
     */
    @Override
    public Order findOrderById(Integer orderId) {
        LOGGER.debug("Order com.test.hp.service: find order by id({})", orderId);

        return orderDao.findByOrderId(orderId);
    }

    /**
     * Find orderDTOs by dates
     *
     * @param from Date from
     * @param to   Date to
     * @return OrderDTOs List
     */
    @Override
    public List<OrderDTO> findOrdersByDates(LocalDate from, LocalDate to) {
        LOGGER.debug("Order com.test.hp.service: find orders by dates {} - {}", from, to);

        return orderDTODao.findOrdersByDates(from, to);
    }

}
