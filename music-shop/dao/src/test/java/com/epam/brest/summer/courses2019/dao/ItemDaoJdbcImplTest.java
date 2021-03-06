package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Item;
import com.epam.brest.summer.courses2019.rest_app.RestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = RestApplication.class)
@Transactional
@Sql({"classpath:/schema.sql", "classpath:/data.sql"})
class ItemDaoJdbcImplTest {

    @Autowired
    @Qualifier("ItemJdbcDao")
    private ItemDao itemDao;

    private final static String ORDER_ID ="orderId";
    private final static String ITEM_ID ="itemId";

    @Test
    void findItemById(){
        assertNotNull(itemDao);
        Item item = itemDao.findByItemId(1);
        assertEquals(item.getItemId().intValue(), 1);
        assertEquals(item.getItemName(), "Gibson Les Paul");
        assertEquals(item.getItemPrice(), new BigDecimal("1100"));
    }

    @Test
    void findAllItems() {
        List<Item> items = itemDao.findAll();
        assertNotNull(items);
        assertFalse(items.isEmpty());
    }


    @Test
    void addItem(){
        int sizeBefore = itemDao.findAll().size();
        Item item = new Item("Guitar", new BigDecimal("1000"));
        itemDao.addItem(item);
        assertEquals((sizeBefore + 1), itemDao.findAll().size());
    }

    @Test
    void updateItem() {
        Item item = itemDao.findByItemId(16);
        item.setItemName("Bla");
        item.setItemPrice(new BigDecimal("500"));
        itemDao.updateItem(item);
        Item newItem = itemDao.findByItemId(item.getItemId());
        assertEquals(item.getItemName(), newItem.getItemName());
        assertEquals(item.getItemPrice(), newItem.getItemPrice());
    }

    @Test
    void deleteItem(){
        int sizeBefore = itemDao.findAll().size();

        itemDao.deleteItem(16);
        assertEquals(sizeBefore-1, itemDao.findAll().size());
    }
}