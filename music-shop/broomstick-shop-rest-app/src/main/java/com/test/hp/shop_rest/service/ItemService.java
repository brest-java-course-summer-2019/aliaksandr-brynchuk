package com.test.hp.shop_rest.service;


import com.test.hp.shop_rest.model.Item;

import java.util.List;

/**
 * Item com.test.hp.service interface
 */
public interface ItemService {
    /**
     * Add item
     * @param item Item
     */
    void addItem(Item item);

    /**
     * Update item
     *
     * @param item Item
     */
    void updateItem(Item item);

    /**
     * Delete item
     *
     * @param itemId Item Id
     */
    void deleteItem(Integer itemId);

    /**
     * Find all items
     *
     * @return Items list
     */
    List<Item>findAllItems();

    /**
     * Find item by id
     *
     * @param itemId Item Id
     * @return Item
     */
    Item findItemById(Integer itemId);
}
