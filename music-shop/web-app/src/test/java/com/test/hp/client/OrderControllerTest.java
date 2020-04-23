package com.test.hp.client;

import com.test.hp.client.api.ItemService;
import com.test.hp.client.api.OrderService;
import com.test.hp.client.model.Order;
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

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:app-context-test.xml"})
class OrderControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mock;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    private final static LocalDate FROM = LocalDate.of(2019, 10, 18);
    private final static LocalDate TO = LocalDate.of(2019, 10, 18);

    @Test
    void orders() throws Exception{
        mock.perform(MockMvcRequestBuilders.get("/outer/order/orders"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=utf-8"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("orders")));

        Mockito.verify(orderService, Mockito.times(1)).findAllOrderDTOs();
    }

    @Test
    void goToAddOrderPage() throws Exception{
        mock.perform(MockMvcRequestBuilders.get("/outer/order/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("order")));

        Mockito.verify(itemService, Mockito.times(1)).findAllItems();
    }

    @Test
    void addOrder() throws Exception{
        mock.perform(MockMvcRequestBuilders.post("/outer/order")
                .param("itemsIds", "1,2,3"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrlTemplate("/outer/order/orders"));

        Mockito.verify(orderService, Mockito.times(1)).addOrder(any(Order.class));
    }

    @Test
    void goToEditOrderPage() throws Exception{
        int id = 1;
        Mockito.when(orderService.findOrderById(anyInt())).thenReturn(createOrder(id));

        mock.perform(MockMvcRequestBuilders.get("/outer/order/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("order")));

        Mockito.verify(orderService, Mockito.times(1)).findOrderById(anyInt());
        Mockito.verify(itemService, Mockito.times(1)).findAllItems();
    }

    @Test
    void updateOrder() throws Exception{
        mock.perform(MockMvcRequestBuilders.post("/outer/order/1")
                .param("itemsIds", "1,2,3"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrlTemplate("/outer/order/orders"));

        Mockito.verify(orderService, Mockito.times(1)).updateOrder(any(Order.class));
    }

    @Test
    void orderview() throws Exception{
        int id = 1;
        Mockito.when(orderService.findOrderById(anyInt())).thenReturn(createOrder(id));

        mock.perform(MockMvcRequestBuilders.get("/outer/order/orderview/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("order")));

        Mockito.verify(orderService, Mockito.times(1)).findOrderById(anyInt());
    }

    @Test
    void findOrdersByDates() throws Exception{
        mock.perform(MockMvcRequestBuilders.get("/outer/order/orders/{from}/{to}", FROM, TO))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=utf-8"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("orders")));

        Mockito.verify(orderService, Mockito.times(1)).findOrdersByDates(FROM, TO);
    }

    @AfterEach
    void afterEach() {
        Mockito.verifyNoMoreInteractions(orderService);
        Mockito.verifyNoMoreInteractions(itemService);
        Mockito.reset(orderService);
        Mockito.reset(itemService);
    }

    private static Order createOrder(Integer orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setItemsList(new ArrayList<>());
        return order;
    }
}
