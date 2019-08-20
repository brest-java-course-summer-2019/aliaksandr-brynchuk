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
     * Item group
     */
    private String itemGroup;

    /**
     * Item name
     */
    private String itemName;

    /**
     * Item status
     */
    private String itemStatus;

    /**
     * Item price;
     */
    private BigDecimal itemPrice;

    public Item(){

    }


    public Item(String itemGroup, String itemName, String itemStatus,  BigDecimal itemPrice) {
        this.itemGroup = itemGroup;
        this.itemName = itemName;
        this.itemStatus = itemStatus;
        this.itemPrice = itemPrice;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemGroup() {
        return itemGroup;
    }

    public void setItemGroup(String itemGroup) {
        this.itemGroup = itemGroup;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(getItemId(), item.getItemId()) &&
                Objects.equals(getItemGroup(), item.getItemGroup()) &&
                Objects.equals(getItemName(), item.getItemName()) &&
                Objects.equals(getItemStatus(), item.getItemStatus()) &&
                Objects.equals(getItemPrice(), item.getItemPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemId(), getItemGroup(), getItemName(), getItemStatus(), getItemPrice());
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemGroup='" + itemGroup + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemStatus='" + itemStatus + '\'' +
                ", itemPrice=" + itemPrice +
                '}';
    }
}
