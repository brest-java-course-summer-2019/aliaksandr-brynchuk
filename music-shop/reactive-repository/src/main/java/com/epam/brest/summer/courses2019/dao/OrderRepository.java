package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Order;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@Qualifier("orderRepository")
public interface OrderRepository extends OrderDao, ReactiveCrudRepository<Order, Integer>, OrderRepositoryCustom {

    @Override
    @Modifying
    Mono<Void> saveOrder(Order order);

    @Override
    @Modifying
    Mono<Void> deleteByOrderId(Integer orderId);

    @Override
    Mono<Order> findByOrderId(Integer orderId);

    @Override
    @Modifying
    @Query(
            value = "delete from order_items where order_id = :id",
            nativeQuery = true
    )
    Mono<Void> clearItemsList(@Param("id") Integer orderId);

    @Override
    @Modifying
    Mono<Void> updateOrderItemsList(Order order);
}
