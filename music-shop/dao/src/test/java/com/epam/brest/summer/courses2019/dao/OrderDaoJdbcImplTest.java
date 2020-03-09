package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Order;
import com.epam.brest.summer.courses2019.rest_app.RestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = RestApplication.class)
@Transactional
class OrderDaoJdbcImplTest {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderDTODao orderDTODao;

    @Test
    void addOrder() {
        Order order = new Order();
        int sizeBefore = orderDTODao.findAll().size();
        orderDao.addOrder(order);
        assertEquals(sizeBefore+1, orderDTODao.findAll().size());
    }


    @Test
    void deleteOrder() {
        Order order = new Order();
        orderDao.addOrder(order);
        int sizeBefore = orderDTODao.findAll().size();
        orderDao.deleteOrder(order.getOrderId());

        assertEquals(sizeBefore-1, orderDTODao.findAll().size());
    }

    @Test
    void findOrderById(){
        assertNotNull(orderDao);
        Integer id = 1;
        Order order = orderDao.findByOrderId(id);
        assertNotNull(order);
        assertEquals(id, order.getOrderId());
    }

    @Test
    void deleteItemsList(){
        int id = 1;

        orderDao.clearItemsList(id);
        assertTrue(orderDao.findByOrderId(id).getItemsList().isEmpty());
    }
}