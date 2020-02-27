package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class ItemJpaDaoImpl implements ItemDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemJpaDaoImpl.class);

    private static final String FIND_NOT_RESERVED_ITEMS = "select i.item_id, i.item_name, i.item_price from items i "+
    "left join order_items io on io.item_id = i.item_id "+
    "where i.item_id not in (select oi.item_id from order_items oi)";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Item addItem(Item item) {
        LOGGER.debug("Item  JPA DAO: add item {}", item);

        entityManager.persist(item);

        return item;
    }

    @Override
    public void updateItem(Item item) {
        LOGGER.debug("Item  JPA DAO: update item {}", item);

        entityManager.persist(item);
    }

    @Override
    public void deleteItem(Integer itemId) {
        LOGGER.debug("Item  JPA DAO: delete item {}", itemId);

        Item item = entityManager.find(Item.class, itemId);
        entityManager.remove(item);
    }

    @Override
    public List<Item> findAllItems() {
        LOGGER.debug("Item  JPA DAO: find not reserved items");

        return entityManager.createNativeQuery(FIND_NOT_RESERVED_ITEMS).getResultList();
    }

    @Override
    public Item findItemById(Integer itemId) {
        LOGGER.debug("Item  JPA DAO: find item by id {}", itemId);

        return entityManager.find(Item.class, itemId);
    }
}
