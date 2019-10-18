package com.epam.brest.summer.courses2019;


import com.epam.brest.summer.courses2019.services.ItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.initMocks;

class ItemServiceImplMockTest {

    @Mock
    private ItemDao itemDao;

    @InjectMocks
    private ItemServiceImpl itemService;

    @Captor
    ArgumentCaptor<Item> captor;

    @BeforeEach
    void setUp() {
        initMocks(this);
        itemService = new ItemServiceImpl(itemDao);
    }

    @Test
    void findAllItems(){
        Mockito.when(itemDao.findAllItems()).thenReturn(Collections.singletonList(createItem()));

        List<Item> items = itemService.findAllItems();

        assertNotNull(items);
        assertEquals(1, items.size());

        Mockito.verify(itemDao).findAllItems();
    }
    

    @Test
    void findById(){
        int id = 1;

        Mockito.when(itemDao.findItemById(id)).thenReturn(Optional.of(createItem()));

        Item item = itemService.findItemById(id);

        assertEquals("Item", item.getItemName());

        Mockito.verify(itemDao).findItemById(id);

    }

    @Test
    void addItem(){
        Item item = createItem();
        Mockito.when(itemDao.addItem(any(Item.class))).thenReturn(item);

        itemService.addItem(item);

        Mockito.verify(itemDao).addItem(item);
    }

    @Test
    void updateItem(){
        itemService.updateItem(createItem());

        Mockito.verify(itemDao).updateItem(captor.capture());

        Item item = captor.getValue();
        assertNotNull(item);
        assertEquals("Item", item.getItemName());
    }

    @Test
    void deleteItem(){
        int id = 1;

        itemService.deleteItem(id);

        Mockito.verify(itemDao).deleteItem(id);
    }


    private static Item createItem() {
        Item item = new Item();
        item.setItemName("Item");
        return item;
    }

}