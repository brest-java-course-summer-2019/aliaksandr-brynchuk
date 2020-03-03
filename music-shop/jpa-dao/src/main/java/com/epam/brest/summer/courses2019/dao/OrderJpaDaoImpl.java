package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Item;
import com.epam.brest.summer.courses2019.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
@Qualifier("OrderJpaDao")
public class OrderJpaDaoImpl implements OrderDao{

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderJpaDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addOrder(Order order) {
        LOGGER.debug("Order JPA DAO: add order {}", order);

        entityManager.persist(order);
    }


    @Override
    public void deleteOrder(Integer orderId) {
        LOGGER.debug("Order JPA DAO: delete order {}", orderId);

        Order order = entityManager.find(Order.class, orderId);
        entityManager.remove(order);
    }

    @Override
    public Order findOrderById(Integer orderId) {
        LOGGER.debug("Order JPA DAO: find order by id {}", orderId);

        Order order = entityManager.find(Order.class, orderId);
        order.setOrderCost((BigDecimal) entityManager.createNativeQuery("select sum(i.item_price) as orderCost " +
                "from orders o " +
                "left join order_items io on o.order_id = io.order_id " +
                "left join items i on i.item_id = io.item_id " +
                "where o.order_id = ?").setParameter(1, orderId).getSingleResult());
        return order;
    }

    @Override
    public void updateOrder(Order order) {
        LOGGER.debug("Order JPA DAO: update order {}", order);

        entityManager.merge(order);
    }
}
