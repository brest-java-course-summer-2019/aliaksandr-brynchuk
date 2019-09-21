package com.epam.brest.summer.courses2019;


import java.util.List;
import java.util.Optional;

public interface ItemService {
    Item addItem(Item item);

    void updateItem(Item item);

    void deleteItem(Integer itemId);

    List<Item>findAllItems();

    Optional<Item> findItemById(Integer itemId);

    List<Item> itemsList(Integer id);
}
