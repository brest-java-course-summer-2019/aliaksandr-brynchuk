package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Order;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("orderRepository")
public interface OrderRepository extends OrderDao, CrudRepository<Order, Integer>, OrderRepositoryCustom {

    @Override
    default void addOrder(Order order) {
        save(order);
    }

    @Override
    @Modifying
    default void deleteOrder(@Param("id") Integer orderId){
        deleteById(orderId);
    };

    @Override
    Order findByOrderId(Integer orderId);

    @Override
    @Modifying
    @Query(
            value = "delete from order_items where order_id = :id",
            nativeQuery = true
    )
    void clearItemsList(@Param("id") Integer orderId);

    @Override
    @Modifying
    void updateOrderItemsList(Order order);
}
