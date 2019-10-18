package com.epam.brest.summer.courses2019.web_app.consumers;


import com.epam.brest.summer.courses2019.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.MockitoAnnotations.initMocks;

class ItemRestConsumerTest {

    @Mock
    private RestTemplate restTemplate;

    private ItemRestConsumer consumer;

    @BeforeEach
    void setUp(){
        initMocks(this);
        consumer = new ItemRestConsumer("url", restTemplate);
    }

    @Test
    void findAllItems(){
        List<Item> items = Arrays.asList(createItem(1), createItem(2));

        Mockito.when(restTemplate.exchange("url/assortment",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Item>>() {}))
                .thenReturn(new ResponseEntity<>(Arrays.asList(createItem(1), createItem(2)), HttpStatus.OK));

        List<Item> result = consumer.findAllItems();

        assertNotNull(result);

        assertEquals(items, result);
    }

    @Test
    void findItemById(){
        int id = 1;
        Item item = createItem(id);
        Mockito.when(restTemplate.getForEntity("url/"+id, Item.class))
                .thenReturn(new ResponseEntity<>(createItem(id), HttpStatus.OK));

        Item result = consumer.findItemById(id);

        assertNotNull(result);

        assertEquals(item.getItemName(), result.getItemName());
        assertEquals(item.getItemPrice(), item.getItemPrice());
    }

    @Test
    void addItem(){
        int id = 1;
        Item item = createItem(id);
        Mockito.when(restTemplate.postForEntity("url", item, Item.class))
                .thenReturn(new ResponseEntity<>(item, HttpStatus.CREATED));

        consumer.addItem(item);

        Mockito.verify(restTemplate).postForEntity("url", item, Item.class);
    }

    @Test
    void updateItem(){
        int id = 1;
        Item item = createItem(id);

        Mockito.doNothing().when(restTemplate).put("url", item);

        consumer.updateItem(item);

        Mockito.verify(restTemplate).put("url", item);
    }

    @Test
    void deleteItem(){
        int id = 1;

        Mockito.doNothing().when(restTemplate).delete("url/"+id+"/delete");

        consumer.deleteItem(id);

        Mockito.verify(restTemplate).delete("url/"+id+"/delete");
    }

    private Item createItem(Integer id){
        Item item = new Item();
        item.setItemId(id);
        item.setItemName("Item");
        item.setItemPrice(new BigDecimal("42"));

        return item;
    }
}
