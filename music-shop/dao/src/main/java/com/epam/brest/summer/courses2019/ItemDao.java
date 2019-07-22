package com.epam.brest.summer.courses2019;


import java.util.List;

public interface ItemDao {

    Item addItem(Item item);

    void updateItem(Item item);

    void deleteItem(Item item);

    List<Item>findAllItems();

}
