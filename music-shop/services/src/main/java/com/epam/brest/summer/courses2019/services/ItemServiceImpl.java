package com.epam.brest.summer.courses2019.services;

import com.epam.brest.summer.courses2019.Item;
import com.epam.brest.summer.courses2019.ItemDao;
import com.epam.brest.summer.courses2019.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private ItemDao dao;

    @Autowired
    public ItemServiceImpl(ItemDao dao){
         this.dao = dao;
    }


    private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Override
    public void addItem(Item item) {
        LOGGER.debug("Add item: {}", item);
        dao.addItem(item);
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
        LOGGER.debug("ItemServiceImpl Find all items DAO - {}", dao);

        return dao.findAllItems();
    }

    @Override
    public Item findItemById(Integer itemId) {
        LOGGER.debug("Find item by id {}", itemId);
        return dao.findItemById(itemId).orElseThrow(()-> new RuntimeException("Failed to get item from DB"));
    }
}