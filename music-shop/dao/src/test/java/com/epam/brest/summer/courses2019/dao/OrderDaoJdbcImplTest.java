package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
@Rollback
class OrderDaoJdbcImplTest {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderDTODao orderDto;

    @Test
    void addOrder() {
        Order order = new Order();
        int sizeBefore = orderDto.findAllOrderDTOs().size();
        orderDao.addOrder(order);
        assertEquals(sizeBefore+1, orderDto.findAllOrderDTOs().size());
    }


    @Test
    void deleteOrder() {
        Order order = new Order();
        orderDao.addOrder(order);
        int sizeBefore = orderDto.findAllOrderDTOs().size();
        orderDao.deleteOrder(order.getOrderId());

        assertEquals(sizeBefore-1, orderDto.findAllOrderDTOs().size());
    }

    @Test
    void findOrderById(){
        assertNotNull(orderDao);
        Integer id = 1;
        Order order = orderDao.findOrderById(id);
        assertNotNull(order);
        assertEquals(id, order.getOrderId());
    }
}