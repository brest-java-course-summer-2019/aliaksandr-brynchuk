package com.epam.brest.summer.courses2019;

import com.epam.brest.summer.courses2019.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Item addItem(Item item);

    void updateItem(Item item);

    void deleteItem(Integer itemId);

    List<Item>findAllItems();

    Optional<Item> findItemById(Integer itemId);

    List<Item> findItemsByGroup(String group);

}
