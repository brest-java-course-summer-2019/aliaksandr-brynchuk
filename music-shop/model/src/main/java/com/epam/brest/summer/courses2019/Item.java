package com.epam.brest.summer.courses2019;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * POJO Item for model
 */

public class Item {
    /**
     * Item id
     */
    private Integer itemId;

    /**
     * Item name
     */
    private String itemName;

    /**
     * Item price;
     */
    private BigDecimal itemPrice;

    /**
     * Item status;
     */
    private boolean isReserved;


    public Item(){
    }


    public Item(String itemName, BigDecimal itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return isReserved() == item.isReserved() &&
                getItemId().equals(item.getItemId()) &&
                getItemName().equals(item.getItemName()) &&
                getItemPrice().equals(item.getItemPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemId(), getItemName(), getItemPrice(), isReserved());
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

