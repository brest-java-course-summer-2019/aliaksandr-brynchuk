package com.test.hp.shop_rest.repository;

import com.test.hp.shop_rest.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ItemRepositoryTest {

    @Autowired
    @Qualifier("itemRepository")
    private ItemDao dao;

    @Autowired
    private TestEntityManager tem;

    @Test
    void findById(){
        int id = 1;
        Item item = dao.findByItemId(id);

        assertNotNull(item);
        assertEquals("Gibson Les Paul", item.getItemName());
    }

    @Test
    void findAll(){
        List<Item> items = dao.findAll();
        assertNotNull(items);
        assertFalse(items.isEmpty());
    }

    @Test
    void addItem(){
        int sizeBefore = dao.findAll().size();
        Item item = new Item("Guitar", new BigDecimal("1000"));
        dao.addItem(item);
        assertEquals((sizeBefore + 1), dao.findAll().size());
    }

    @Test
    void updateItem() {
        Item item = tem.find(Item.class, 2);
        tem.clear();

        item.setItemName("Bla");
        item.setItemPrice(new BigDecimal("500"));

        dao.updateItem(item);

        Item newItem = dao.findByItemId(2);

        assertEquals(item.getItemName(), newItem.getItemName());
        assertEquals(item.getItemPrice(), newItem.getItemPrice());
    }

    @Test
    void deleteItem(){
        int sizeBefore = dao.findAll().size();

        dao.deleteItem(16);
        assertEquals(sizeBefore-1, dao.findAll().size());
    }

}
