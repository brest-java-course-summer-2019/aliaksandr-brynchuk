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
     * Item firm
     */
    private String itemFirm;

    /**
     * Item name
     */
    private String itemName;

    /**
     * Item price;
     */
    private BigDecimal itemPrice;

    public Item(){

    }


    public Item(Integer itemId, String itemGroup, String itemFirm, String itemName, BigDecimal itemPrice) {
        this.itemId = itemId;
        this.itemGroup = itemGroup;
        this.itemFirm = itemFirm;
        this.itemName = itemName;
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

    public void setItemFirm(String itemFirm) {
        this.itemFirm = itemFirm;
    }

    public String getItemFirm() {
        return itemFirm;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(getItemId(), item.getItemId()) &&
                Objects.equals(getItemGroup(), item.getItemGroup()) &&
                Objects.equals(getItemFirm(), item.getItemFirm()) &&
                Objects.equals(getItemName(), item.getItemName()) &&
                Objects.equals(getItemPrice(), item.getItemPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemId(), getItemGroup(), getItemFirm(), getItemName(), getItemPrice());
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemGroup='" + itemGroup + '\'' +
                ", itemFirm='" + itemFirm + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                '}';
    }
}

