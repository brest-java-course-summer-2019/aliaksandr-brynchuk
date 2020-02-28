package com.epam.brest.summer.courses2019.services;

import com.epam.brest.summer.courses2019.dao.ItemDao;
import com.epam.brest.summer.courses2019.dao.OrderDTODao;
import com.epam.brest.summer.courses2019.dao.OrderDao;
import com.epam.brest.summer.courses2019.model.Order;
import com.epam.brest.summer.courses2019.model.OrderDTO;
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
     * @param itemDao Item DAO
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
        LOGGER.debug("Order service: add order:  {}", order);

        order.setOrderDate(LocalDate.now());
        orderDao.addOrder(order);
    }

    /**
     * Update order
     *
     * @param order Order
     */
    @Override
    public void updateOrder(Order order) {
        LOGGER.debug("Order service: update order: {}", order);

        orderDao.updateOrder(order);
    }

    /**
     * Delete order
     *
     * @param orderId Order ID
     */
    @Override
    public void deleteOrder(Integer orderId) {
        LOGGER.debug("Order service: delete order:  {}", orderId);

        orderDao.deleteOrder(orderId);
    }

    /**
     * Find all orderDTOs
     *
     * @return OrderDTOs List
     */
    @Override
    @Transactional(readOnly=true)
    public List<OrderDTO> findAllOrderDTOs() {
        LOGGER.debug("Order service: find all orders");

        return orderDTODao.findAllOrderDTOs();
    }

    /**
     * Find order by ID
     *
     * @param orderId Order ID
     * @return Order
     */
    @Override
    public Order findOrderById(Integer orderId) {
        LOGGER.debug("Order service: find order by id({})", orderId);

        return orderDao.findOrderById(orderId);
    }

    /**
     * Find orderDTOs by dates
     *
     * @param from Date from
     * @param to Date to
     * @return OrderDTOs List
     */
    @Override
    @Transactional(readOnly=true)
    public List<OrderDTO> findOrdersByDates(LocalDate from, LocalDate to) {
        LOGGER.debug("Order service: find orders by dates {} - {}", from,to);

        return orderDTODao.findOrdersByDates(from, to);
    }

}
