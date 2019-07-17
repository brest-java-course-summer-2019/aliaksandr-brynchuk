package com.epam.brest.summer.courses2019;

import com.epam.brest.summer.courses2019.Item;

import org.junit.Assert;
import org.junit.Test;

public class ItemTest {

    Item paddle = Item.createItem(
            1,
            "Guitar",
            "paddleWithStrings",
            0);

    @Test
    public void getItemId() {
        Assert.assertEquals(paddle.getItemId().intValue(), 1);
    }


    @Test
    public void getItemGroup() {
        Assert.assertEquals(paddle.getItemGroup(), "Guitar");
    }

    @Test
    public void getItemName() {
        Assert.assertEquals(paddle.getItemName(), ("paddleWithStrings"));
    }

    @Test
    public void getItemPrice() {
        Assert.assertEquals(paddle.getItemPrice().intValue(), 0);
    }


}
