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

    /**
     * Order number
     */
    private Integer orderNumber;

    /**
     * Order date
     */
    private LocalDate date;

    /**
     * Items list
     */
    private List<Item> itemsList;

    /**
     * Order cost
     */
    private BigDecimal orderCost;


    private Order(Integer orderId, Integer orderNumber, LocalDate date) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.date = date;
    }

    private Order(Integer orderId, Integer orderNumber, LocalDate date, List<Item> itemsList, BigDecimal orderCost) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.date = date;
        this.itemsList = itemsList;
        this.orderCost = orderCost;
    }

    public Order createEmptyOrder(Integer orderId, Integer orderNumber, LocalDate date) {
        return new Order(orderId, orderNumber, date);
    }

    public Order createOrderWithItems(
            Integer orderId,
            Integer orderNumber,
            LocalDate date,
            List<Item> itemsList,
            BigDecimal orderCost) {
        return new Order(orderId, orderNumber, date, itemsList, orderCost);
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
                ", orderNumber=" + orderNumber +
                ", date=" + date +
                ", itemsList=" + itemsList +
                ", orderCost=" + orderCost +
                '}';
    }
}
