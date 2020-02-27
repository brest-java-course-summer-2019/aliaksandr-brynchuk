package com.epam.brest.summer.courses2019.services;

import com.epam.brest.summer.courses2019.model.Item;
import com.epam.brest.summer.courses2019.model.Order;
import com.epam.brest.summer.courses2019.model.OrderDTO;
import com.epam.brest.summer.courses2019.dao.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
     * Item DAO field
     */
    private ItemDao itemDao;

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
    public OrderServiceImpl(OrderDao orderDao, ItemDao itemDao, OrderDTODao orderDTODao) {
        this.orderDao = orderDao;
        this.itemDao = itemDao;
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
//        updateOrderItems(order);
    }

//    /**
//     * Update order items list
//     * this private method is used in addOrder and updateOrder methods
//     * Items add by id, item status changes to the "true" value cuz item will be added to order
//     *
//     * @param order Order
//     */
//    private void updateOrderItems(Order order) {
//        LOGGER.debug("Order service: update order items {}", order);
//
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("orderId", order.getOrderId());
//
//        order.getItemsIds().
//                forEach(item-> {
//                    parameters.addValue("itemId", item);
//                    itemDao.insertItem(parameters); });
//    }

    /**
     * Update order
     *
     * @param order Order
     */
    @Override
    public void updateOrder(Order order) {
        LOGGER.debug("Order service: update order: {}", order);

//        List<Item> items = itemDao.itemsListFromOrder(order.getOrderId());

//        itemDao.deleteItemsList(order.getOrderId());
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

        Order orderById = orderDao.findOrderById(orderId);
//        orderById.setItemsList(itemDao.itemsListFromOrder(orderId));
        return orderById;
    }

    /**
     * Find orderDTOs by dates
     *
     * @param from Date from
     * @param to Date to
     * @return OrderDTOs List
     */
    @Override
    public List<OrderDTO> findOrdersByDates(LocalDate from, LocalDate to) {
        LOGGER.debug("Order service: find orders by dates {} - {}", from,to);

        return orderDTODao.findOrdersByDates(from, to);
    }

}
