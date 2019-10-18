package com.epam.brest.summer.courses2019;


import java.util.List;

public interface ItemService {
    void addItem(Item item);

    void updateItem(Item item);

    void deleteItem(Integer itemId);

    List<Item>findAllItems();

    Item findItemById(Integer itemId);
}
