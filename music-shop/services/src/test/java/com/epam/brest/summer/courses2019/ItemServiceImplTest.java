package com.epam.brest.summer.courses2019;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-services.xml"})

@Rollback
class ItemServiceImplTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemDao itemDao;

    private Item create(){
        Item item = new Item();
        item.setItemId(10);
        item.setItemName("Guitar");
        return item;
    }

    @Test
    void findAllItems(){
        List<Item> items = itemService.findAllItems();
        assertNotNull(items);
        assertFalse(items.isEmpty());
    }

    @Test
    void findItemById(){
       int id = 1;
       Item item = itemService.findItemById(id);

       assertNotNull(item);
       assertEquals("Gibson Les Paul", item.getItemName());
    }

    @Test
    void itemsList(){
        int id = 1;
        List<Item> items = itemDao.itemsListFromOrder(id);
        assertNotNull(items);
        assertEquals(3, items.size());
    }

    @Test
    void addItem(){
        int sizeBefore = itemService.findAllItems().size();
        Item newItem = create();
        itemService.addItem(newItem);
        int sizeAfter = itemService.findAllItems().size();
        assertEquals(sizeAfter, sizeBefore + 1);
    }

    @Test
    void updateItem(){
        Item item = create();
        itemService.updateItem(item);
        Item item1 = itemService.findItemById(item.getItemId());
        assertNotNull(item1);
        assertEquals("Guitar", item1.getItemName());
    }

    @Test
    void deleteItem(){
        Item item = create();
        itemService.addItem(item);
        int countBefore = itemService.findAllItems().size();
        itemService.deleteItem(item.getItemId());

        assertEquals(countBefore-1, itemService.findAllItems().size());
    }


}
