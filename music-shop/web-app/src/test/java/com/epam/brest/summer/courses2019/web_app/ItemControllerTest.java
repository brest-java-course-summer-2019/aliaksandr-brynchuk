package com.epam.brest.summer.courses2019.web_app;

import com.epam.brest.summer.courses2019.Item;
import com.epam.brest.summer.courses2019.ItemService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:app-context-test.xml"})
class ItemControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mock;

    @Autowired
    private ItemService service;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void allItems() throws Exception{
        mock.perform(MockMvcRequestBuilders.get("/outer/item/assortment"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=utf-8"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("assortment")));

        Mockito.verify(service, Mockito.times(1)).findAllItems();
    }

    @Test
    void goToAddItemPage() throws Exception{
        mock.perform(MockMvcRequestBuilders.get("/outer/item"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("item")));
    }

    @Test
    void addItem() throws Exception{
        mock.perform(MockMvcRequestBuilders.post("/outer/item")
                .param("itemId", "1")
                .param("itemName", "Item")
                .param("itemPrice", "42"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrlTemplate("/outer/item/assortment"));

        Mockito.verify(service, Mockito.times(1)).addItem(any(Item.class));
    }

    @Test
    void goToEditItemPage() throws Exception{
        int id = 1;
        Mockito.when(service.findItemById(Mockito.anyInt())).thenReturn(createItem(id));

        mock.perform(MockMvcRequestBuilders.get("/outer/item/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("item")));

        Mockito.verify(service, Mockito.times(1)).findItemById(anyInt());
    }

    @Test
    void updateItem() throws Exception{
        mock.perform(MockMvcRequestBuilders.post("/outer/item/1")
                .param("itemId", "1")
                .param("itemName", "Item")
                .param("itemPrice", "42"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrlTemplate("/outer/item/assortment"));

        Mockito.verify(service, Mockito.times(1)).updateItem(any(Item.class));
    }

    @Test
    void deleteItem() throws Exception{
        mock.perform(MockMvcRequestBuilders.get("/outer/item/1/delete"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrlTemplate("/outer/item/assortment"));

        Mockito.verify(service, Mockito.times(1)).deleteItem(anyInt());
    }

    @AfterEach
    void afterEach() {
        Mockito.verifyNoMoreInteractions(service);
        Mockito.reset(service);
    }

    private static Item createItem(Integer itemId) {
        Item item = new Item();
        item.setItemId(itemId);
        item.setItemName("Item");
        item.setItemPrice(new BigDecimal("42"));
        return item;
    }
}