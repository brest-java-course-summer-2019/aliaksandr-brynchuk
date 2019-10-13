package com.epam.brest.summer.courses2019;

import com.epam.brest.summer.courses2019.services.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class OrderServiceImplMockTest {
    @Mock
    OrderDao orderDao;

    @Mock
    ItemDao itemDao;

    @Mock
    OrderDTODao orderDTODao;

    @InjectMocks
    OrderServiceImpl orderService;

    @Captor
    ArgumentCaptor<Order> captor;

    @BeforeEach
    void setUp(){
        initMocks(this);
        orderService = new OrderServiceImpl(orderDao, itemDao, orderDTODao);
    }

    @Test
    void findAllDTOs(){
        Mockito.when(orderDTODao.findAllOrderDTOs()).thenReturn(Collections.singletonList(createDto()));
        List<OrderDTO> orders = orderService.findAllOrderDTOs();
        assertNotNull(orders);
        assertEquals(1, orders.size());
    }

    @Test
    void findOrderById(){
        int id = 1;
        Mockito.when(orderDao.findOrderById(id)).thenReturn(create());

        Order order = orderService.findOrderById(id);

        assertTrue(order.getOrderId().equals(1));
        Mockito.verify(orderDao).findOrderById(id);
    }

//    @Test
//    void findOrdersByDates(){
//        Mockito.when(orderDTODao.findOrdersByDates(LocalDate.now(), LocalDate.now()))
//                .thenReturn(Collections.singletonList(createDto()));
//
//        List<OrderDTO> orders = orderService.findOrdersByDates(LocalDate.now(), LocalDate.now());
//        assertEquals(1, orders.size());
//
//        Mockito.verify(orderDTODao).findOrdersByDates(LocalDate.now(), LocalDate.now());
//    }

    @Test
    void addOrder(){
//        Order order = create();
//        Mockito.when(orderDao.addOrder(order), itemDao.)
    }

    @Test
    void updateOrder(){
    }

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
