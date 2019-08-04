package com.epam.brest.summer.courses2019;

import com.epam.brest.summer.courses2019.Item;

import org.junit.Assert;
import org.junit.Test;

public class ItemTest {

    Item paddle = new Item();

    @Test
    public void getItemId() {
        paddle.setItemId(1);
        Assert.assertEquals(paddle.getItemId().intValue(), 1);
    }


    @Test
    public void getItemGroup() {
        paddle.setItemGroup("Guitar");
        Assert.assertEquals(paddle.getItemGroup(), "Guitar");
    }

    @Test
    public void getItemName() {
        paddle.setItemName("paddleWithStrings");
        Assert.assertEquals(paddle.getItemName(), ("paddleWithStrings"));
    }

    /*@Test
    public void getItemPrice() {
        paddle.setItemPrice(0);
        Assert.assertEquals(paddle.getItemPrice(), (0));*/
    }
