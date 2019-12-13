package com.epam.brest.summer.courses2019.rest_app;

import com.epam.brest.summer.courses2019.model.Item;
import com.epam.brest.summer.courses2019.services.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:rest-app-test.xml"})
class ItemRestControllerTest {

    @Autowired
    private ItemRestController controller;

    @Autowired
    private ItemService service;

    private ObjectMapper mapper = new ObjectMapper();

    private MockMvc mock;

    @BeforeEach
    void setUp(){
        mock = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @AfterEach
    void reset(){
        Mockito.reset(service);
    }

    @Test
    void findAllItems() throws Exception{
        Mockito.when(service.findAllItems()).thenReturn(Arrays.asList(createItem(1), createItem(2)));
        mock.perform(MockMvcRequestBuilders.get("/inner/item/assortment")
        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].itemId", Matchers.is(1)))
                .andExpect(jsonPath("$[0].itemName", Matchers.is("Item")))
                .andExpect(jsonPath("$[1].itemId", Matchers.is(2)))
                .andExpect(jsonPath("$[1].itemName", Matchers.is("Item")));

        Mockito.verify(service, Mockito.times(1)).findAllItems();
    }

    @Test
    void findItemById() throws Exception{
        int id = 1;
        Mockito.when(service.findItemById(id)).thenReturn(createItem(id));

        mock.perform(MockMvcRequestBuilders.get("/inner/item/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemId", Matchers.is(1)))
                .andExpect(jsonPath("$.itemName", Matchers.is("Item")))
                .andExpect(jsonPath("$.itemPrice", Matchers.is(42)));

        Mockito.verify(service, Mockito.times(1)).findItemById(id);
    }

    @Test
    void addItem() throws Exception{
        int id = 1;

        Mockito.doNothing().when(service).addItem(createItem(id));

        mock.perform(MockMvcRequestBuilders.post("/inner/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createItem(id)))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(service, Mockito.times(1)).addItem(any(Item.class));
    }

    @Test
    void updateItem() throws Exception{
        int id = 1;

        Mockito.doNothing().when(service).updateItem(createItem(id));

        mock.perform(MockMvcRequestBuilders.put("/inner/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createItem(id)))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

        Mockito.verify(service, Mockito.times(1)).updateItem(any(Item.class));
    }

    @Test
    void deleteItem() throws Exception{
        int id = 1;

        Mockito.doNothing().when(service).deleteItem(id);

        mock.perform(MockMvcRequestBuilders.delete("/inner/item/{id}/delete", id))
                .andExpect(status().isOk());

        Mockito.verify(service, Mockito.times(1)).deleteItem(id);
    }

    private Item createItem(int itemId) {
        Item item = new Item();
        item.setItemId(itemId);
        item.setItemName("Item");
        item.setItemPrice(new BigDecimal("42"));
        return item;
    }
}
