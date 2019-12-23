package com.epam.brest.summer.courses2019.dao;


import com.epam.brest.summer.courses2019.model.Item;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;
import java.util.Optional;

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
     * Insert item to order
     *
     * @param parameters Parameters to sql request
     */
    void insertItem(MapSqlParameterSource parameters);

    /**
     * Delete item
     *
     * @param itemId Item Id
     */
    void deleteItem(Integer itemId);

    /**
     * Clear order items list
     *
     * @param orderId Order id
     */
    void deleteItemsList(Integer orderId);

    /**
     *Get all items
     *
     * @return Items List
     */
    List<Item> findAllItems();

    /**
     * Get items list from order
     *
     * @param orderId Order ID
     * @return Items list
     */
    List<Item> itemsListFromOrder(Integer orderId);

    /**
     *Find item by id
     *
     * @param itemId Item ID
     * @return Optional<>Item</>
     */
    Optional<Item> findItemById(Integer itemId);

    /**
     * Change item status
     *
     * @param itemId Item ID
     * @param status Item status
     */
    void changeItemStatus(Integer itemId, boolean status);
}
