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
public class OrderDtoDaoJdbcImplTest {

    @Autowired
    OrderDTODao orderDTODao;

    @Test
    public void findAllOrderDTOs(){
        assertNotNull(orderDTODao);
        List<OrderDTO> orders = orderDTODao.findAllOrderDTOs();
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
    }

    @Test
    public void findOrdersByDates() {
        LocalDate from = LocalDate.of(2019, 8,1);
        LocalDate to = LocalDate.of(2019, 8,2);

        List<Order> orders = orderDTODao.findOrdersByDates(from, to);

        assertNotNull(orders);

    }
}
