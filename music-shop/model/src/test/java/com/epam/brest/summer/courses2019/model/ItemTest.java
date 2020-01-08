package com.epam.brest.summer.courses2019.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {

   private Item paddle = new Item();

    @Test
    public void getItemId() {
        paddle.setItemId(1);
        assertEquals(paddle.getItemId().intValue(), 1);
    }

    @Test
    public void getItemName() {
        paddle.setItemName("paddleWithStrings");
        assertEquals(paddle.getItemName(), ("paddleWithStrings"));
    }

    @Test
    public void getItemPrice() {
        paddle.setItemPrice(new BigDecimal("1"));
        assertEquals(paddle.getItemPrice(), new BigDecimal("1"));
    }
}