package com.test.hp.shop_rest.controllers;//package com.epam.brest.summer.courses2019.rest_app;
//
//import com.epam.brest.summer.courses2019.model.Item;
//import com.epam.brest.summer.courses2019.services.ItemService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.math.BigDecimal;
//import java.util.Arrays;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(ItemRestController.class)
//class ItemRestControllerTest {
//
//    @MockBean
//    private ItemService com.test.hp.service;
//
//    private ObjectMapper mapper = new ObjectMapper();
//
//    @Autowired
//    private MockMvc mock;
//
//    @Test
//    void findAllItems() throws Exception{
//        Mockito.when(com.test.hp.service.findAllItems()).thenReturn(Arrays.asList(createItem(1), createItem(2)));
//        mock.perform(MockMvcRequestBuilders.get("/items")
//        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].itemId", Matchers.is(1)))
//                .andExpect(jsonPath("$[0].itemName", Matchers.is("Item")))
//                .andExpect(jsonPath("$[1].itemId", Matchers.is(2)))
//                .andExpect(jsonPath("$[1].itemName", Matchers.is("Item")));
//
//        Mockito.verify(com.test.hp.service, Mockito.times(1)).findAllItems();
//    }
//
//    @Test
//    void findItemById() throws Exception{
//        int id = 1;
//        Mockito.when(com.test.hp.service.findItemById(id)).thenReturn(createItem(id));
//
//        mock.perform(MockMvcRequestBuilders.get("/items/{id}", id)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.itemId", Matchers.is(1)))
//                .andExpect(jsonPath("$.itemName", Matchers.is("Item")))
//                .andExpect(jsonPath("$.itemPrice", Matchers.is(42)));
//
//        Mockito.verify(com.test.hp.service, Mockito.times(1)).findItemById(id);
//    }
//
//    @Test
//    void addItem() throws Exception{
//        int id = 1;
//
//        Mockito.doNothing().when(com.test.hp.service).addItem(createItem(id));
//
//        mock.perform(MockMvcRequestBuilders.post("/items")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(createItem(id)))
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//        Mockito.verify(com.test.hp.service, Mockito.times(1)).addItem(any(Item.class));
//    }
//
//    @Test
//    void updateItem() throws Exception{
//        int id = 1;
//
//        Mockito.doNothing().when(com.test.hp.service).updateItem(createItem(id));
//
//        mock.perform(MockMvcRequestBuilders.put("/items/{id}", id)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(createItem(id)))
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isAccepted());
//
//        Mockito.verify(com.test.hp.service, Mockito.times(1)).updateItem(any(Item.class));
//    }
//
//    @Test
//    void deleteItem() throws Exception{
//        int id = 1;
//
//        Mockito.doNothing().when(com.test.hp.service).deleteItem(id);
//
//        mock.perform(MockMvcRequestBuilders.delete("/items/{id}", id))
//                .andExpect(status().isOk());
//
//        Mockito.verify(com.test.hp.service, Mockito.times(1)).deleteItem(id);
//    }
//
//    private Item createItem(int itemId) {
//        Item item = new Item();
//        item.setItemId(itemId);
//        item.setItemName("Item");
//        item.setItemPrice(new BigDecimal("42"));
//        return item;
//    }
//}
