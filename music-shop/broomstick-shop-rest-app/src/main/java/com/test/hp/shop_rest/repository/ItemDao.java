package com.test.hp.shop_rest.repository;

import com.test.hp.shop_rest.model.Item;

import java.util.List;

/**
 *Item DAO interface
 */
public interface ItemDao {

    /**
     * Add item to db
     *
     * @param item Item
     * @return Item
     */
    Item addItem(Item item);

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
     *Get all items
     *
     * @return Items List
     */
    List<Item> findAll();

    /**
     *Find item by id
     *
     * @param itemId Item ID
     * @return Optional<>Item</>
     */
    Item findByItemId(Integer itemId);
}
