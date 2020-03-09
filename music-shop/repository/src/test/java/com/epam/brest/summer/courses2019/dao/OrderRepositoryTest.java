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
        Order order = new Order();
        dao.addOrder(order);

        List<Item> itemsBefore = order.getItemsList();

        Item item1 = new Item("qwe", new BigDecimal("123"));
        Item item2 = new Item("qwe1", new BigDecimal("124"));

        itemDao.addItem(item1);
        itemDao.addItem(item2);

        List<String> itemsIds = new ArrayList<>();
        itemsIds.add(item1.getItemId().toString());
        itemsIds.add(item2.getItemId().toString());
        order.setItemsIds(itemsIds);

        dao.updateOrderItemsList(order);
        Order order1 = dao.findByOrderId(order.getOrderId());

    }
}
