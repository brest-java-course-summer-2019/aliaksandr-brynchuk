package com.epam.brest.summer.courses2019.model;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * POJO Order for model
 */
@Entity
@Table(name = "orders")
@SqlResultSetMapping(
        name="OrderResult",
        entities = {
                @EntityResult(
                        entityClass = Order.class,
                        fields = {
                                @FieldResult(name = "orderId", column = "order_id"),
                                @FieldResult(name = "orderDate", column = "order_date"),
                                @FieldResult(name = "orderCost", column = "order_cost")
                        }
                )
        }
)
@NamedNativeQuery(
        name="OrderWithCostById",
        query=""
)
public class Order implements Serializable {
    /**
     * Order id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * Order date
     */
    @Column(name = "order_date")
    private LocalDate orderDate;

    /**
     * Items list
     */
    @JoinTable(
            name="order_items",
            joinColumns = @JoinColumn(name="order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> itemsList;


    /**
     * Items ids list
     */
    @Transient
    private List<String> itemsIds;

    /**
     * Order cost
     */
    @Transient
    private BigDecimal orderCost;

    /**
     * get Order id
     *
     * @return Order id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * set Order id
     *
     * @param orderId Order id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * get Order date
     *
     * @return Order Date
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }

    /**
     * set Order Date
     *
     * @param orderDate Order Date
     */
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * get Items list
     *
     * @return Items List
     */
    public List<Item> getItemsList() {
        return itemsList;
    }

    /**
     * set Items list
     *
     * @param itemsList Items List
     */
    public void setItemsList(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    /**
     * get Order cost
     *
     * @return Order Cost
     */
    public BigDecimal getOrderCost() {
        return orderCost;
    }

    /**
     * set Order cost
     *
     * @param orderCost Order Cost
     */
    public void setOrderCost(BigDecimal orderCost) {
        this.orderCost = orderCost;
    }

    /**
     * get IDs of items from items list
     *
     * @return Items Ids
     */
    public List<String> getItemsIds() {
        return itemsIds;
    }

    /**
     * set IDs of items from items list
     *
     * @param itemsIds Items IDs
     */
    public void setItemsIds(List<String> itemsIds) {
        this.itemsIds = itemsIds;
    }


    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", itemsList=" + itemsList +
                ", itemsIds=" + itemsIds +
                ", orderCost=" + orderCost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getOrderId(), order.getOrderId()) &&
                Objects.equals(getOrderDate(), order.getOrderDate()) &&
                Objects.equals(getItemsList(), order.getItemsList()) &&
                Objects.equals(getItemsIds(), order.getItemsIds()) &&
                Objects.equals(getOrderCost(), order.getOrderCost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getOrderDate(), getItemsList(), getItemsIds(), getOrderCost());
    }
}
