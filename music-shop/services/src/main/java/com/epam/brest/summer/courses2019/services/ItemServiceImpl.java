package com.epam.brest.summer.courses2019.services;

import com.epam.brest.summer.courses2019.Item;
import com.epam.brest.summer.courses2019.ItemDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class ItemServiceImpl implements ItemService{

    private ItemDao dao;

    public ItemServiceImpl(ItemDao dao){
         this.dao = dao;
    }


    private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Override
    public Item addItem(Item item) {
        LOGGER.debug("Add item: {}", item);
        return dao.addItem(item);
    }

    @Override
    public void updateItem(Item item) {
        LOGGER.debug("Update item: {}", item);
        dao.updateItem(item);
    }

    @Override
    public void deleteItem(Integer itemId) {
        LOGGER.debug("Delete item {}", itemId);
        dao.deleteItem(itemId);
    }

    @Override
    public List<Item> findAllItems() {
        LOGGER.debug("Find all items");
        return dao.findAllItems();
    }

    @Override
    public List<Item> findItemsByGroup(String group) {
        LOGGER.debug("Find all items by group {}", group);
        return dao.findItemsByGroup(group);
    }

    @Override
    public Optional<Item> findItemById(Integer itemId) {
        LOGGER.debug("Find item by id {}", itemId);
        return dao.findItemById(itemId);
    }
}