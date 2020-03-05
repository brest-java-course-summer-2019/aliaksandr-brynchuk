import com.epam.brest.summer.courses2019.dao.ItemDao;
import com.epam.brest.summer.courses2019.model.Item;
import com.epam.brest.summer.courses2019.rest_app.RestApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = RestApplication.class)
@Transactional
@Sql({"classpath:/schema.sql", "classpath:/data.sql"})
public class ItemJpaDaoImplTest {

    @Autowired
    private ItemDao dao;

    @Test
    void findAll(){
        List<Item> items = dao.findAllItems();
        assertFalse(items.isEmpty());
    }

    @Test
    void findById(){

    }

    @Test
    void addItem(){
        Item item = new Item();

        int sizeBefore = dao.findAllItems().size();

        dao.addItem(item);

        assertEquals(sizeBefore+1, dao.findAllItems().size());
    }

    @Test
    void updateItem(){

    }

    @Test
    void deleteItem(){

    }
}
