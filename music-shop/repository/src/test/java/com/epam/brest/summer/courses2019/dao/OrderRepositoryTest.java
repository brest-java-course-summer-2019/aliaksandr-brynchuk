package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Item;
import com.epam.brest.summer.courses2019.model.Order;
import com.epam.brest.summer.courses2019.rest_app.RestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = RestApplication.class)
@Transactional
@Sql({"classpath:/schema.sql", "classpath:/data.sql"})
public class OrderRepositoryTest {

    @Autowired
    @Qualifier("orderRepository")
    private OrderDao dao;

    @Autowired
    @Qualifier("itemRepository")
    private ItemDao itemDao;

    @Test
    void findById(){
        Order order = dao.findByOrderId(1);
    }

    @Test
    void updateOrderItemsList(){
    }
}
