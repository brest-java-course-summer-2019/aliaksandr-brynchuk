package com.epam.brest.summer.courses2019;

import com.epam.brest.summer.courses2019.services.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Collections;

import static org.mockito.MockitoAnnotations.initMocks;

public class OrderServiceImplMockTest {
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

    }

    private static OrderDTO createDto(){
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(1);
        return dto;
    }
}
