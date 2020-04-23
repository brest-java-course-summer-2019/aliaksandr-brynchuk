package com.epam.brest.summer.courses2019.services;

import com.epam.brest.summer.courses2019.dao.ItemDao;
import com.epam.brest.summer.courses2019.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public ItemServiceImpl(@Qualifier("itemRepository") ItemDao dao){
         this.dao = dao;
    }
//    @Autowired
//    public ItemServiceImpl(@Qualifier("itemJpaDao") ItemDao dao){
//        this.dao = dao;
//    }

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
        LOGGER.debug("Item service: add item: {}", item);
        dao.addItem(item);
    }

    /**
     * Update item
     *
     * @param item Item
     */
    @Override
    public void updateItem(Item item) {
        LOGGER.debug("Item service: update item: {}", item);
        dao.updateItem(item);
    }

    /**
     * Delete item
     *
     * @param itemId Item Id
     */
    @Override
    public void deleteItem(Integer itemId) {
        LOGGER.debug("Item service: delete item {}", itemId);
        dao.deleteItem(itemId);
    }

    /**
     * Delete item
     *
     * @return
     */
    @Override
    public List<Item> findAllItems() {
        LOGGER.debug("ItemService: find all items");

        return dao.findAll();
    }

    /**
     * Find item by id
     *
     * @param itemId Item Id
     * @return Item
     */
    @Override
    public Item findItemById(Integer itemId) {
        LOGGER.debug("Item service: find item by id {}", itemId);

        return dao.findByItemId(itemId);
    }
}