package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Order;
import com.epam.brest.summer.courses2019.rest_app.RestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = RestApplication.class)
@AutoConfigureTestEntityManager
@Transactional
@Sql({"classpath:/schema.sql", "classpath:/data.sql"})
public class OrderJpaDaoImplTest {

    @Autowired
    @Qualifier("orderJpaDao")
    private OrderDao orderDao;

    @Autowired
    @Qualifier("orderDtoJpaDao")
    private OrderDTODao orderDTODao;

    @Autowired
    private TestEntityManager tem;

    @Test
    void addOrder() {
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        int sizeBefore = orderDTODao.findAll().size();
        orderDao.addOrder(order);
        assertEquals(sizeBefore+1, orderDTODao.findAll().size());
    }


    @Test
    void deleteOrder() {
        int sizeBefore = orderDTODao.findAll().size();
        orderDao.deleteOrder(1);

        assertEquals(sizeBefore-1, orderDTODao.findAll().size());
    }

    @Test
    void findOrderById(){
        Order order = orderDao.findByOrderId(1);
        assertNotNull(order);
    }

    @Test
    void clearItemsList(){
        int id = 1;

        orderDao.clearItemsList(id);
        assertTrue(orderDao.findByOrderId(id).getItemsList().isEmpty());
    }

    @Test
    void updateOrderItemsList(){
        List<String> itemsIds = new ArrayList<>();
        itemsIds.add("15");
        itemsIds.add("16");

        Order order = new Order();
        order.setOrderDate(LocalDate.now());

        tem.persist(order);
        tem.clear();

        order.setItemsIds(itemsIds);

        orderDao.updateOrderItemsList(order);

        assertFalse(orderDao.findByOrderId(order
                .getOrderId())
                .getItemsList()
                .isEmpty());
    }
}
