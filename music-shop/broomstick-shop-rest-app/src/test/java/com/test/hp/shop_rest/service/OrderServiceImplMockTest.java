package com.test.hp.shop_rest.service;

import com.test.hp.shop_rest.model.Order;
import com.test.hp.shop_rest.model.OrderDTO;
import com.test.hp.shop_rest.repository.OrderDTODao;
import com.test.hp.shop_rest.repository.OrderDao;
import com.test.hp.shop_rest.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class OrderServiceImplMockTest {
    @Mock
    OrderDao orderDao;

    @Mock
    OrderDTODao orderDTODao;

    @InjectMocks
    OrderServiceImpl orderService;

    @BeforeEach
    void setUp(){
        initMocks(this);
        orderService = new OrderServiceImpl(orderDao, orderDTODao);
    }

    private final static LocalDate FROM = LocalDate.of(2019, 10, 18);
    private final static LocalDate TO = LocalDate.of(2019, 10, 18);

    @Test
    void findAllDTOs(){
        Mockito.when(orderDTODao.findAll()).thenReturn(Collections.singletonList(createDto()));

        List<OrderDTO> orders = orderService.findAllOrderDTOs();

        assertNotNull(orders);
        assertEquals(1, orders.size());

        Mockito.verify(orderDTODao, Mockito.times(1)).findAll();
    }

    @Test
    void findOrderById(){
        int id = 1;
        Mockito.when(orderDao.findByOrderId(id)).thenReturn(create());

        Order order = orderService.findOrderById(id);

        assertTrue(order.getOrderId().equals(1));

        Mockito.verify(orderDao, Mockito.times(1)).findByOrderId(id);
    }

    @Test
    void findOrdersByDates(){
        Mockito.when(orderDTODao.findOrdersByDates(FROM, TO))
                .thenReturn(Collections.singletonList(createDto()));

        List<OrderDTO> orders = orderService.findOrdersByDates(FROM, TO);
        assertEquals(1, orders.size());

        Mockito.verify(orderDTODao, Mockito.times(1)).findOrdersByDates(FROM, TO);
    }

    @Test
    void addOrder(){
        Order order = create();
        Mockito.doNothing().when(orderDao).addOrder(order);

        order.setItemsIds(Arrays.asList());

        orderService.addOrder(order);

        Mockito.verify(orderDao, Mockito.times(1)).addOrder(order);

    }
//
//    @Test
//    void updateOrder(){
//        Order order = create();
//
//        order.setItemsIds(Arrays.asList("1"));
//
//        orderService.updateOrder(order);
//
//        Mockito.verify(itemDao, Mockito.times(1)).deleteItemsList(order.getOrderId());
//        Mockito.verify(itemDao, Mockito.times(1))
//                .insertItem(any(MapSqlParameterSource.class));
//    }


    @Test
    void deleteOrder(){
        int id = 1;

        orderService.deleteOrder(id);

        Mockito.verify(orderDao).deleteOrder(id);

    }

    private static Order create(){
        Order order = new Order();
        order.setOrderId(1);
        return order;
    }

    private static OrderDTO createDto(){
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(1);
        return dto;
    }
}
