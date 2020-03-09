package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.OrderDTO;
import com.epam.brest.summer.courses2019.rest_app.RestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = RestApplication.class)
@Transactional
class OrderDtoDaoJdbcImplTest {

    @Autowired
    private OrderDTODao orderDTODao;

    @Test
    void findAllOrderDTOs(){
        assertNotNull(orderDTODao);
        List<OrderDTO> orders = orderDTODao.findAll();
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
    }

    @Test
    void findOrdersByDates() {
        LocalDate from = LocalDate.of(2019, 8,1);
        LocalDate to = LocalDate.of(2019, 8,2);

        List<OrderDTO> orders = orderDTODao.findOrdersByDates(from, to);
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
    }
}
