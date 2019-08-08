package com.epam.brest.summer.courses2019;


import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ItemDao {

    Item addItem(Item item);

    void updateItem(Item item);

    void insertItem(MapSqlParameterSource parameters);

    void deleteItem(Integer itemId);

    void deleteItemsList(Integer orderId);

    List<Item> itemsListFromOrder(Integer orderId);

    List<Item>findAllItems();

    Optional<Item> findItemById(Integer itemId);

    List<Item> findItemsByGroup(String group);

    List<Item> itemsList(final String sqlRequest, MapSqlParameterSource parameters);

}
