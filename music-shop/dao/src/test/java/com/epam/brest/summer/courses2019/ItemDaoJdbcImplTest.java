package com.epam.brest.summer.courses2019;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
@Rollback

class ItemDaoJdbcImplTest {

    @Autowired
    ItemDao itemDao;

    @Test
    void findItemById(){
        assertNotNull(itemDao);
        Item item = itemDao.findItemById(1).get();
        assertEquals(item.getItemId().intValue(), 1);
        assertEquals(item.getItemName(), "Gibson Les Paul");
        assertEquals(item.getItemPrice(), new BigDecimal("1100"));
    }

    @Test
    void findAllItems() {
        List<Item> items = itemDao.findAllItems();
        assertNotNull(items);
        assertFalse(items.isEmpty());
    }


    @Test
    void itemsListFromOrder(){
        List<Item> items = itemDao.itemsListFromOrder(1);
        assertNotNull(items);
        assertEquals(items.size(), 3);
    }

    @Test
    void addItem() {
        List<Item> items = itemDao.findAllItems();
        int sizeBefore = items.size();
        Item item = new Item("Guitar", new BigDecimal("1000"));
        Item newItem = itemDao.addItem(item);
        assertNotNull(newItem.getItemId());
        assertEquals(newItem.getItemName(), item.getItemName());
        assertEquals(newItem.getItemPrice(), item.getItemPrice());
        assertEquals((sizeBefore + 1), itemDao.findAllItems().size());
    }

    @Test
    void updateItem() {
        Item item = itemDao.findItemById(2).get();
        item.setItemName("Bla");
        item.setItemPrice(new BigDecimal("500"));
        itemDao.updateItem(item);
        Item newItem = itemDao.findItemById(item.getItemId()).get();
        assertEquals(item.getItemName(), newItem.getItemName());
        assertEquals(item.getItemPrice(), newItem.getItemPrice());

    }


}