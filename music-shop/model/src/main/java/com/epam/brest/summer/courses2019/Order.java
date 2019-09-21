package com.epam.brest.summer.courses2019;


import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.List;

/**
 * POJO Order for model
 */

public class Order {
    /**
     * Order id
     */
    private Integer orderId;


//    /**
//     * Order number
//     */
//    private Integer orderNumber;

    /**
     * Order date
     */
    private LocalDate orderDate;


    /**
     * Items list
     */
    private List<Item> itemsList;

    /**
     * Order cost
     */
    private BigDecimal orderCost;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

//    public Integer getOrderNumber() {
//        return orderNumber;
//    }
//
//    public void setOrderNumber(Integer orderNumber) {
//        this.orderNumber = orderNumber;
//    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public List<Item> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    public BigDecimal getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(BigDecimal orderCost) {
        this.orderCost = orderCost;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
//                ", orderNumber=" + orderNumber +
                ", orderDate=" + orderDate +
                ", itemsList=" + itemsList +
                ", orderCost=" + orderCost +
                '}';
    }
}
