package com.epam.brest.summer.courses2019.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * POJO Order for model
 */

public class OrderDTO {

        /**
         * Order id
         */
        private Integer orderId;

        /**
         * Order date
         */
        private LocalDate orderDate;

        /**
         * Order cost
         */
        private BigDecimal orderCost;

    /**
     * get Order DTO id
     *
     * @return Order DTO id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * set Order DTO id
     *
     * @param orderId Order DTO id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * get Order DTO date
     *
     * @return Order DTO date
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }

    /**
     * set Order DTO date
     *
     * @param orderDate Order DTO date
     */
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * get Order DTO cost
     *
     * @return Order DTO cost
     */
    public BigDecimal getOrderCost() {
        return orderCost;
    }

    /**
     * set Order cost
     *
     * @param orderCost order cost
     */
    public void setOrderCost(BigDecimal orderCost) {
        this.orderCost = orderCost;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", orderCost=" + orderCost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDTO)) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(getOrderId(), orderDTO.getOrderId()) &&
                Objects.equals(getOrderDate(), orderDTO.getOrderDate()) &&
                Objects.equals(getOrderCost(), orderDTO.getOrderCost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getOrderDate(), getOrderCost());
    }
}

