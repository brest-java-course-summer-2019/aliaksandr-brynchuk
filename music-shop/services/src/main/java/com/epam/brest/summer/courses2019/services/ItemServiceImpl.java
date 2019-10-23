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

/**
 * Item Service implementation
 */
@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    /**
     * Item DAO field
     */
    private ItemDao dao;

    /**
     * Constructor, injection item dao bean
     * @param dao Item DAO
     */
    @Autowired
    public ItemServiceImpl(ItemDao dao){
         this.dao = dao;
    }

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);

    /**
     * Add item
     *
     * @param item Item
     */
    @Override
    public void addItem(Item item) {
        LOGGER.debug("Add item: {}", item);
        dao.addItem(item);
    }

    /**
     * Update item
     *
     * @param item Item
     */
    @Override
    public void updateItem(Item item) {
        LOGGER.debug("Update item: {}", item);
        dao.updateItem(item);
    }

    /**
     * Delete item
     *
     * @param itemId Item Id
     */
    @Override
    public void deleteItem(Integer itemId) {
        LOGGER.debug("Delete item {}", itemId);
        dao.deleteItem(itemId);
    }

    /**
     * Delete item
     *
     * @return
     */
    @Override
    public List<Item> findAllItems() {
        LOGGER.debug("ItemServiceImpl Find all items DAO - {}", dao);

        return dao.findAllItems();
    }

    /**
     * Find item by id
     *
     * @param itemId Item Id
     * @return Item
     */
    @Override
    public Item findItemById(Integer itemId) {
        LOGGER.debug("Find item by id {}", itemId);
        return dao.findItemById(itemId).orElseThrow(()-> new RuntimeException("Failed to get item from DB"));
    }
}