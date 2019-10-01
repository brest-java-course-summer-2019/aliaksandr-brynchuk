package com.epam.brest.summer.courses2019;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
@Rollback
public class OrderDaoJdbcImplTest {

    @Autowired
    OrderDao orderDao;

    @Test
    public void addOrder() {
    }

    @Test
    public void updateOrder() {
    }

    @Test
    public void deleteOrder() {
    }

    @Test
    public void findAllOrders() {
//        assertNotNull(orderDao);


    }


    @Test
    public void findOrderById(){
        assertNotNull(orderDao);
        Integer id = 1;
        Order order = orderDao.findOrderById(id);
        assertNotNull(order);
    }
}