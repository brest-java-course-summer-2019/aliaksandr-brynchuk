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
}

