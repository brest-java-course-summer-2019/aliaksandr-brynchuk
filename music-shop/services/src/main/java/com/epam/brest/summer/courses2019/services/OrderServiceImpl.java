package com.epam.brest.summer.courses2019.services;

import com.epam.brest.summer.courses2019.dao.OrderDTODao;
import com.epam.brest.summer.courses2019.dao.OrderDao;
import com.epam.brest.summer.courses2019.model.Order;
import com.epam.brest.summer.courses2019.model.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<Void> addOrder(Order order) {
        LOGGER.debug("Order service: add order:  {}", order);

        order.setOrderDate(LocalDate.now());

        orderDao.saveOrder(order);

        orderDao.updateOrderItemsList(order);

        return Mono.empty();
    }

    @Override
    public Mono<Void> updateOrder(Order order) {
        LOGGER.debug("Order service: update order: {}", order);

        orderDao.clearItemsList(order.getOrderId());

        orderDao.updateOrderItemsList(order);
        return Mono.empty();
    }

    /**
     * Delete order
     *
     * @param orderId Order ID
     */
    @Override
    public Mono<Void> deleteOrder(Integer orderId) {
        LOGGER.debug("Order service: delete order:  {}", orderId);

       return orderDao.deleteByOrderId(orderId);
    }

    /**
     * Find all orderDTOs
     *
     * @return OrderDTOs List
     */
    @Override
    public Flux<OrderDTO> findAllOrderDTOs() {
        LOGGER.debug("Order service: find all orders");

        return orderDTODao.findAll();
    }

    /**
     * Find order by ID
     *
     * @param orderId Order ID
     * @return Order
     */
    @Override
    public Mono<Order> findOrderById(Integer orderId) {
        LOGGER.debug("Order service: find order by id({})", orderId);

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
    public Flux<OrderDTO> findOrdersByDates(LocalDate from, LocalDate to) {
        LOGGER.debug("Order service: find orders by dates {} - {}", from, to);

        return orderDTODao.findOrdersByDates(from, to);
    }

}
