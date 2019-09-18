package com.epam.brest.summer.courses2019;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * POJO Order for model
 */

public class OrderDTO {

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
        private Date orderDate;

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

        public Integer getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(Integer orderNumber) {
            this.orderNumber = orderNumber;
        }

        public Date getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(Date orderDate) {
            this.orderDate = orderDate;
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
                    ", orderDate=" + orderDate +
                    ", orderCost=" + orderCost +
                    '}';
        }
    }

