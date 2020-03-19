package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("ItemJpaDao")
public class ItemJpaDaoImpl implements ItemDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemJpaDaoImpl.class);

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

        entityManager.merge(item);
    }

    @Override
    public void deleteItem(Integer itemId) {
        LOGGER.debug("Item  JPA DAO: delete item {}", itemId);

        Item item = entityManager.find(Item.class, itemId);
        entityManager.remove(item);
    }

    @Override
    public List<Item> findAll() {
        LOGGER.debug("Item  JPA DAO: find not reserved items");

        return entityManager.createNamedQuery("getNotReservedItems", Item.class).getResultList();
    }

    @Override
    public Item findByItemId(Integer itemId) {
        LOGGER.debug("Item  JPA DAO: find item by id {}", itemId);

        return entityManager.find(Item.class, itemId);
    }
}
