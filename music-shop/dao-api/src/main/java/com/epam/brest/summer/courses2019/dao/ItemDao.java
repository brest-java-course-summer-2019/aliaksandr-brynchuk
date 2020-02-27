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
    List<Item> findAllItems();

    /**
     *Find item by id
     *
     * @param itemId Item ID
     * @return Optional<>Item</>
     */
    Item findItemById(Integer itemId);
}
