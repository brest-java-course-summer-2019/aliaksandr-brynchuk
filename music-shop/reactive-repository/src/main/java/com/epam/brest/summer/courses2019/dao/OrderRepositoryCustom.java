package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Order;
import reactor.core.publisher.Mono;

public interface OrderRepositoryCustom {

    Mono<Void> updateOrderItemsList(Order order);
}
