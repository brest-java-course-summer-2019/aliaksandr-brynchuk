package com.test.hp.shop_rest.repository;

import com.test.hp.shop_rest.model.OrderDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class OrderDtoRepositoryTest {

    @Autowired
    @Qualifier("orderDtoRepository")
    private OrderDTODao dao;

    @Test
    void findAllOrderDTOs(){
        List<OrderDTO> orders = dao.findAll();
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
    }

    @Test
    void findOrdersByDates() {
        LocalDate from = LocalDate.of(2019, 8,1);
        LocalDate to = LocalDate.of(2019, 8,2);

        List<OrderDTO> orders = dao.findOrdersByDates(from, to);
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
    }
}
