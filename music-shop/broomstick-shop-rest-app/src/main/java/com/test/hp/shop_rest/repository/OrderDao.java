package com.test.hp.shop_rest.repository;

import com.test.hp.shop_rest.model.Order;

/**
 *Order DAO interface
 */
public interface OrderDao {
    /**
     * add order to db
     *
     * @param order Order
     */
    void addOrder(Order order);

    /**
     * Delete order from db
     *
     * @param orderId Order Id
     */
    void deleteOrder(Integer orderId);

    /**
     * Find order by id
     *
     * @param orderId Order Id
     * @return Order
     */
    Order findByOrderId(Integer orderId);

    void clearItemsList(Integer orderId);

    void updateOrderItemsList(Order order);

}
