package com.epam.brest.summer.courses2019;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
@Rollback

public class ItemDaoJdbcImplTest {

    @Autowired
    ItemDao itemDao;



    private static Item item;

    @BeforeAll
    static void init() {
        item = new Item("Guitars", "Strat", new BigDecimal("1000"));
    }

    @Test
    void findItemById(){
        assertNotNull(itemDao);
        Item item = itemDao.findItemById(1).get();
        assertEquals(item.getItemId().intValue(), 1);
        assertEquals(item.getItemGroup(), "Guitars");
        assertEquals(item.getItemName(), "Les Paul");
        assertEquals(item.getItemPrice(), new BigDecimal("1100"));
    }

    @Test
    void findAllAvailabItems() {
        List<Item> items = itemDao.findAllAvailableItems();
        assertNotNull(items);
        assertFalse(items.isEmpty());
        assertEquals(items.size(), 9);
    }

    @Test
    void findItemByGroup(){
        List<Item> items = itemDao.findItemsByGroup("Guitars");
        assertNotNull(items);
        assertFalse(items.isEmpty());
        assertEquals(items.size(), 3);
    }

    @Test
    void itemsListFromOrder(){
        List<Item> items = itemDao.itemsListFromOrder(1);
        assertNotNull(items);
        assertEquals(items.size(), 3);
    }

    @Test
    void addItem() {
        List<Item> items = itemDao.findAllAvailableItems();
        int sizeBefore = items.size();
        Item newItem = itemDao.addItem(item);
        assertNotNull(newItem.getItemId());
        assertEquals(newItem.getItemGroup(), item.getItemGroup());
        assertEquals(newItem.getItemName(), item.getItemName());
        assertEquals(newItem.getItemPrice(), item.getItemPrice());
        assertEquals((sizeBefore + 1), itemDao.findAllAvailableItems().size());
    }

    @Test
    void updateItem() {
        Item item = itemDao.findItemById(2).get();
        item.setItemGroup("Guitars");
        item.setItemName("Bla");
        item.setItemPrice(new BigDecimal("500"));
        itemDao.updateItem(item);
        Item newItem = itemDao.findItemById(item.getItemId()).get();
        assertEquals(item.getItemGroup(), newItem.getItemGroup());
        assertEquals(item.getItemName(), newItem.getItemName());
        assertEquals(item.getItemPrice(), newItem.getItemPrice());

    }

    @Test
    void deleteItem() {
        itemDao.addItem(item);
        List<Item> items = itemDao.findAllAvailableItems();
        int sizeBefore = items.size();
        itemDao.deleteItem(item.getItemId());
        assertEquals(sizeBefore - 1, itemDao.findAllAvailableItems().size());

    }

    @Test
    void insertItem(){
        List<Item> items = itemDao.itemsListFromOrder(1);
        int sizeBefore = items.size();

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("order_id", 1);
        parameters.addValue("item_id", )
        itemDao.insertItem();

    }


}