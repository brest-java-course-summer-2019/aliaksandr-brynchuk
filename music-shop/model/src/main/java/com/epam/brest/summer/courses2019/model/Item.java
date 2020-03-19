package com.epam.brest.summer.courses2019.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * POJO Item for model
 */

@NamedNativeQuery(
        name = "getNotReservedItems",
        query = "select i.item_id, i.item_name, i.item_price from items i " +
                "left join order_items io on io.item_id = i.item_id " +
                "where i.item_id not in (select oi.item_id from order_items oi)",
        resultClass = Item.class
)
@Entity
@Table(name = "items")
public class Item{
    /**
     * Item id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer itemId;

    /**
     * Item name
     */
    @Column(name = "item_name")
    private String itemName;

    /**
     * Item price;
     */
    @Column(name = "item_price")
    private BigDecimal itemPrice;

    /**
     * Constructor without parameters
     */
    public Item(){
    }


    /**
     * Constructor with parameters
     *
     * @param itemName Item name
     * @param itemPrice Item price
     */
    public Item(String itemName, BigDecimal itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    /**
     * Get Item id
     *
     * @return Item Id
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * Set Item id
     *
     * @param itemId Item id
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * Get Item name
     *
     * @return Item Name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * set Item name
     *
     * @param itemName Item Name
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * get Item price
     *
     * @return Item Price
     */
    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    /**
     * set Item price
     *
     * @param itemPrice Item Price
     */
    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getItemId().equals(item.getItemId()) &&
                getItemName().equals(item.getItemName()) &&
                getItemPrice().equals(item.getItemPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemId(), getItemName(), getItemPrice());
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                '}';
    }
}

