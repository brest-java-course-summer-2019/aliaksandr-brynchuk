import com.epam.brest.summer.courses2019.dao.OrderDao;
import com.epam.brest.summer.courses2019.model.Order;
import com.epam.brest.summer.courses2019.rest_app.RestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = RestApplication.class)
@Transactional
public class OrderJpaDaoImplTest {

    @Autowired
    private OrderDao orderDao;

//    @Test
//    void updateOrder(){
//
//        Order order = new Order();
//
//
//    }

    @Test
    void findOrderById(){
        Order order = orderDao.findOrderById(1);
        System.out.println(order);


    }
}
