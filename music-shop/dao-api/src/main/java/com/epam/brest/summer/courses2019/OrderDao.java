package com.epam.brest.summer.courses2019;

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
    Order findOrderById(Integer orderId);

}
