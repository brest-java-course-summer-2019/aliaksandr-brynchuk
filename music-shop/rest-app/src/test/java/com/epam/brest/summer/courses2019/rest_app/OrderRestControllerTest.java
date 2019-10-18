package com.epam.brest.summer.courses2019.rest_app;

import com.epam.brest.summer.courses2019.Order;
import com.epam.brest.summer.courses2019.OrderDTO;
import com.epam.brest.summer.courses2019.OrderService;
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

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:rest-app-test.xml"})
class OrderRestControllerTest {

    @Autowired
    private OrderRestController controller;

    @Autowired
    private OrderService service;

    private MockMvc mock;

    private ObjectMapper mapper = new ObjectMapper();

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
    void findAllOrders() throws Exception{
        Mockito.when(service.findAllOrderDTOs()).thenReturn(Arrays.asList(createDto(1), createDto(2)));
        mock.perform(MockMvcRequestBuilders.get("/inner/order/orders")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId", Matchers.is(1)))
                .andExpect(jsonPath("$[1].orderId", Matchers.is(2)));

        Mockito.verify(service, times(1)).findAllOrderDTOs();
    }

    @Test
    void findOrderById() throws Exception{
        int id = 1;
        Mockito.when(service.findOrderById(id)).thenReturn(createOrder(id));

        mock.perform(MockMvcRequestBuilders.get("/inner/order/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId", Matchers.is(1)));

        Mockito.verify(service, times(1)).findOrderById(id);
    }

    @Test
    void addOrder() throws Exception{
        int id = 1;
        Mockito.doNothing().when(service).addOrder(createOrder(id));

        mock.perform(MockMvcRequestBuilders.post("/inner/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createOrder(id)))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        Mockito.verify(service, times(1)).addOrder(any(Order.class));
    }

    @Test
    void updateOrder() throws Exception{
        int id = 1;
        Mockito.doNothing().when(service).updateOrder(createOrder(id));

        mock.perform(MockMvcRequestBuilders.put("/inner/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createOrder(id)))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

        Mockito.verify(service, Mockito.times(1)).updateOrder(any(Order.class));
    }

    @Test
    void deleteOrder() throws Exception{
        int id = 1;
        Mockito.doNothing().when(service).deleteOrder(id);

        mock.perform(MockMvcRequestBuilders.delete("/inner/order/{id}/delete", id))
                .andExpect(status().isOk());

        Mockito.verify(service, Mockito.times(1)).deleteOrder(id);
    }

    private OrderDTO createDto(Integer orderId) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(orderId);
        return dto;
    }

    private Order createOrder(Integer orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setItemsIds(Arrays.asList());
        return order;
    }
}
