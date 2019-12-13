package com.epam.brest.summer.courses2019.model;

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

    /**
     * get Item status
     *
     * @return Is Reserved, if value = true item is in order
     */
    public boolean getIsReserved() {
        return isReserved;
    }

    /**
     * set Item status
     *
     * @param reserved Is Reserved
     */
    public void setIsReserved(boolean reserved) {
        isReserved = reserved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getIsReserved() == item.getIsReserved() &&
                getItemId().equals(item.getItemId()) &&
                getItemName().equals(item.getItemName()) &&
                getItemPrice().equals(item.getItemPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemId(), getItemName(), getItemPrice(), getIsReserved());
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", isReserved=" + isReserved +
                '}';
    }
}

