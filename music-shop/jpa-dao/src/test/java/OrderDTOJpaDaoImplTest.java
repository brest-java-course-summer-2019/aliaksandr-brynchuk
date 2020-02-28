import com.epam.brest.summer.courses2019.dao.ItemDao;
import com.epam.brest.summer.courses2019.dao.OrderDTODao;
import com.epam.brest.summer.courses2019.dao.OrderDao;
import com.epam.brest.summer.courses2019.model.Item;
import com.epam.brest.summer.courses2019.model.Order;
import com.epam.brest.summer.courses2019.model.OrderDTO;
import com.epam.brest.summer.courses2019.rest_app.RestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = RestApplication.class)
@Transactional
@Sql({"classpath:/schema.sql", "classpath:/data.sql"})
public class OrderDTOJpaDaoImplTest {

    @Autowired
    private OrderDTODao dao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ItemDao itemDao;


    @Test
    void findAllDTOs(){

        Order order1 = orderDao.findOrderById(1);

        Item item1 = itemDao.findItemById(1);
        Item item2 = itemDao.findItemById(2);

        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);

        System.out.println(order1.getItemsList());
        order1.setItemsList(items);

        orderDao.updateOrder(order1);
        System.out.println(order1.getItemsList());

        List<OrderDTO> orders = dao.findAllOrderDTOs();

        for(OrderDTO o: orders){
            System.out.println(o.getOrderCost());
        }
        assertFalse(orders.isEmpty());
    }

    @Test
    void findOrdersByDates(){
        LocalDate from = LocalDate.of(2019, 8,1);
        LocalDate to = LocalDate.of(2019, 8,2);

        List<OrderDTO> orders = dao.findOrdersByDates(from, to);
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
    }
}
