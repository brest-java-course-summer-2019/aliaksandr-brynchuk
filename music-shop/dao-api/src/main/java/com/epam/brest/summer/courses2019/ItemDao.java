package com.epam.brest.summer.courses2019;


import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;
import java.util.Optional;

public interface ItemDao {

    Item addItem(Item item);

    void updateItem(Item item);

    void insertItem(MapSqlParameterSource parameters);

    void deleteItem(Integer itemId);

    void deleteItemsList(Integer orderId);

    List<Item> itemsListFromOrder(Integer orderId);

    List<Item>findAllAvailableItems();

    Optional<Item> findItemById(Integer itemId);

    Optional<Item> findItemByName(String itemName);

    List<Item> findItemsByGroup(String group);
}
