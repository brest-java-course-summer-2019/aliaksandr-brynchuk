package com.test.hp.shop_rest.repository.impl;

import com.test.hp.shop_rest.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderRepositoryCustomImpl.class);

    @Override
    public void updateOrderItemsList(Order order) {
        LOGGER.debug("Order DATA JPA DAO: update items list {}", order);
        StringBuilder insert = new StringBuilder("insert into order_items (order_id, item_id) values ");

        for (int i = 0; i < order.getItemsIds().size(); i++) {
            String temp = "(" + order.getOrderId() + ", " + order.getItemsIds().get(i) + "),";
            insert.append(temp);
        }
        insert.deleteCharAt(insert.length() - 1);

       em.createNativeQuery(insert.toString()).executeUpdate();
    }
}
