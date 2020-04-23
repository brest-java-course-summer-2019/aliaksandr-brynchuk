package com.test.hp.shop_rest.model;

import org.springframework.data.annotation.Immutable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * POJO Order for model
 */
@Entity
@Table(name = "orders")
@Immutable
@SqlResultSetMapping(
        name = "OrderDTOResult",
        classes = {
                @ConstructorResult(
                        targetClass = OrderDTO.class,
                        columns = {
                                @ColumnResult(name = "order_id", type = Integer.class),
                                @ColumnResult(name = "order_date", type = LocalDate.class),
                                @ColumnResult(name = "orderCost", type = BigDecimal.class)
                        }
                )
        }
)
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getOrdersDTOWithCost",

                query = "select o.order_id, o.order_date, sum(i.item_price) as orderCost " +
                        "from orders o " +
                        "left join order_items io on o.order_id = io.order_id " +
                        "left join items i on i.item_id = io.item_id " +
                        "group by o.order_id",

                resultSetMapping = "OrderDTOResult"
        ),
        @NamedNativeQuery(
                name = "getOrdersDTOWithCostByDates",

                query = "select o.order_id, o.order_date, sum(i.item_price) as orderCost " +
                        "from orders o " +
                        "left join order_items io on o.order_id = io.order_id " +
                        "left join items i on i.item_id = io.item_id " +
                        "where order_date between :from and :to group by order_date, o.order_id",

                resultSetMapping = "OrderDTOResult"
        )
})
public class OrderDTO {

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
     * Order cost
     */
    @Transient
    private BigDecimal orderCost;

    public OrderDTO() {
    }

    public OrderDTO(LocalDate orderDate, BigDecimal orderCost) {
        this.orderDate = orderDate;
        this.orderCost = orderCost;
    }

    public OrderDTO(Integer orderId, LocalDate orderDate, BigDecimal orderCost) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderCost = orderCost;
    }

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

