package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Item;
import com.epam.brest.summer.courses2019.model.OrderDTO;
import com.epam.brest.summer.courses2019.rest_app.RestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ContextConfiguration(classes = RestApplication.class)
@Sql({"classpath:/schema.sql", "classpath:/data.sql"})
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
