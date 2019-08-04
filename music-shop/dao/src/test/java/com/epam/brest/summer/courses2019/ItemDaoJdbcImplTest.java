package com.epam.brest.summer.courses2019;

import org.junit.jupiter.api.BeforeAll;
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

public class ItemDaoJdbcImplTest {

    @Autowired
    ItemDao itemDao;

    private static Item item;

    @BeforeAll
    static void init(){
        item = new Item(1, "Guitars", "Fender", "Strat", new BigDecimal(1000));
    }


    @Test
    void addItem() {
        List<Item> items = itemDao.findAllItems();
        int sizeBefore = items.size();
        Item newItem = itemDao.addItem(item);
        assertNotNull(newItem.getItemId());
        assertEquals(newItem.getItemFirm(), item.getItemFirm());
        assertEquals(newItem.getItemGroup(), item.getItemGroup());
        assertEquals(newItem.getItemName(), item.getItemName());
        assertEquals(newItem.getItemPrice(), item.getItemPrice());
        assertTrue((sizeBefore+1) == itemDao.findAllItems().size());
    }

    @Test
    void updateItem() {
    }

    @Test
    void deleteItem() {
        itemDao.addItem(item);
        List<Item> items = itemDao.findAllItems();
        int sizeBefore = items.size();
        itemDao.deleteItem(item.getItemId());
        assertTrue(sizeBefore-1 == itemDao.findAllItems().size());

    }

    @Test
    void findAllItems() {
        List<Item> items = itemDao.findAllItems();
        assertNotNull(items);
        assertFalse(items.isEmpty());
    }
}
