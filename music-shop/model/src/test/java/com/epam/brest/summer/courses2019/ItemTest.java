package com.epam.brest.summer.courses2019;


import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ItemTest {

   private Item paddle = new Item();

    @Test
    public void getItemId() {
        paddle.setItemId(1);
        Assert.assertEquals(paddle.getItemId().intValue(), 1);
    }

    @Test
    public void getItemName() {
        paddle.setItemName("paddleWithStrings");
        Assert.assertEquals(paddle.getItemName(), ("paddleWithStrings"));
    }

    @Test
    public void getItemPrice() {
        paddle.setItemPrice(new BigDecimal("1"));
        Assert.assertEquals(paddle.getItemPrice(), new BigDecimal("1"));
    }
}