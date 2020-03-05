package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Item;
import com.epam.brest.summer.courses2019.rest_app.RestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = RestApplication.class)
@Transactional
class ItemDaoJdbcImplTest {

    @Autowired
    private ItemDao itemDao;

    private final static String ORDER_ID ="orderId";
    private final static String ITEM_ID ="itemId";

    @Test
    void findItemById(){
        assertNotNull(itemDao);
        Item item = itemDao.findItemById(1);
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
        Item item = itemDao.findItemById(2);
        item.setItemName("Bla");
        item.setItemPrice(new BigDecimal("500"));
        itemDao.updateItem(item);
        Item newItem = itemDao.findItemById(item.getItemId());
        assertEquals(item.getItemName(), newItem.getItemName());
        assertEquals(item.getItemPrice(), newItem.getItemPrice());

    }

    @Test
    void deleteItem(){
        Item item = new Item();
        itemDao.addItem(item);

        int sizeBefore = itemDao.findAllItems().size();

        itemDao.deleteItem(item.getItemId());
        assertEquals(sizeBefore-1, itemDao.findAllItems().size());
    }
}