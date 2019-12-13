package com.epam.brest.summer.courses2019.model;

import com.epam.brest.summer.courses2019.model.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

class OrderTest {

    private Order order = new Order();

    @Test
    void getOrderId(){
        order.setOrderId(1);
        assertEquals(order.getOrderId().intValue(), 1);
    }

    @Test
    void getItemsList(){
    }

    @Test
    void getOrderCost(){
        order.setOrderCost(new BigDecimal("100"));
        assertEquals(order.getOrderCost(), new BigDecimal("100"));
    }


}