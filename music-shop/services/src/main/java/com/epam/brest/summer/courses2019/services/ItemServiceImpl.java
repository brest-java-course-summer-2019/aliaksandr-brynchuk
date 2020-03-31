package com.epam.brest.summer.courses2019.services;

import com.epam.brest.summer.courses2019.dao.ItemDao;
import com.epam.brest.summer.courses2019.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<Void> addItem(Item item) {
        LOGGER.debug("Item service: add item: {}", item);
       return dao.saveItem(item);
    }

    /**
     * Update item
     *
     * @param item Item
     */
    @Override
    public Mono<Void> updateItem(Item item) {
        LOGGER.debug("Item service: update item: {}", item);
        return dao.saveItem(item);
    }

    /**
     * Delete item
     *
     * @param itemId Item Id
     */
    @Override
    public Mono<Void> deleteItem(Integer itemId) {
        LOGGER.debug("Item service: delete item {}", itemId);
        return dao.deleteByItemId(itemId);
    }

    /**
     * Delete item
     *
     * @return
     */
    @Override
    public Flux<Item> findAllItems() {
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
    public Mono<Item> findItemById(Integer itemId) {
        LOGGER.debug("Item service: find item by id {}", itemId);

        return dao.findByItemId(itemId);
    }
}