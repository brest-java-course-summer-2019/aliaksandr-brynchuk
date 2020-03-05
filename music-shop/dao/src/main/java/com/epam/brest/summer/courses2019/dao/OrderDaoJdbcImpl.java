package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Item;
import com.epam.brest.summer.courses2019.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Order DAO implementation, gets data from data base
 */
@Repository
@PropertySource("classpath:/sql.properties")
@Qualifier("OrderJdbcDao")
public class OrderDaoJdbcImpl implements OrderDao {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDaoJdbcImpl.class);

    /**
     * NamedParameterJdbcTemplate field
     */
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     *SQL from property file. Find order by id
     */
    @Value("${order.findById}")
    private String findById;

    /**
     *SQL from property file. Add order
     */
    @Value("${order.addOrder}")
    private String addOrder;

    /**
     *SQL from property file. Delete order
     */
    @Value("${order.deleteOrder}")
    private String deleteOrder;

    /**
      *SQL from property file. Insert item to order
        */
    @Value("${item.insertItem}")
    private String insertItem;

        /**
     *SQL from property file. Clear items list
     */
    @Value("${item.deleteItemsFromOrder}")
    private String deleteItemsFromOrder;

    /**
     *SQL from property file. Find all items from order
     */
    @Value("${item.selectItemsFromOrder}")
    private String selectItemsFromOrder;

    /**
     * Constant fields
     */
    private final static String ORDER_ID = "orderId";
    private final static String ORDER_DATE = "orderDate";

    /**
     * Constructor, inject namedParameterJdbcTemplate bean
     *
     * @param namedParameterJdbcTemplate namedParameterJdbcTemplate bean
     */
    public OrderDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

        /**
     * Get items list from order
     *
     * @param orderId Order ID
     * @return Items list
     */
    private List<Item> itemsListFromOrder(Integer orderId) {
        LOGGER.debug("Item DAO: find items from order({})", orderId);

        MapSqlParameterSource parameters = new MapSqlParameterSource(ORDER_ID, orderId);
        return namedParameterJdbcTemplate.query(selectItemsFromOrder, parameters, BeanPropertyRowMapper.newInstance(Item.class));
    }

    /**
     * Find Order by id
     *
     * @param orderId Order Id
     * @return Order
     */
    @Override
    public Order findOrderById(Integer orderId) {
        LOGGER.debug("Order DAO: find order by id({})", orderId);

        MapSqlParameterSource parameters = new MapSqlParameterSource(ORDER_ID, orderId);
        Order order = namedParameterJdbcTemplate.queryForObject(findById, parameters, BeanPropertyRowMapper.newInstance(Order.class));
        order.setItemsList(itemsListFromOrder(orderId));
        return order;
    }

        @Override
        public void updateOrderItemsList(Order order) {
        LOGGER.debug("Order service: update order items {}", order);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("orderId", order.getOrderId());

        order.getItemsIds().
                forEach(item-> {
                    parameters.addValue("itemId", item);
                    namedParameterJdbcTemplate.update(insertItem, parameters); });
    }

    /**
     * Add order to db
     *
     * @param order Order
     */
    @Override
    public void addOrder(Order order) {
        LOGGER.debug("Order DAO:add order ({})", order);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(ORDER_DATE, order.getOrderDate());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(addOrder, parameters, generatedKeyHolder);
        order.setOrderId(generatedKeyHolder.getKey().intValue());
    }


    /**
     * Delete Order from db
     *
     * @param orderId Order Id
     */
    @Override
    public void deleteOrder(Integer orderId) {
        LOGGER.debug("Order DAO: delete order ({})", orderId);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(ORDER_ID, orderId);

        namedParameterJdbcTemplate.update(deleteOrder, parameters);
    }

    @Override
    public void clearItemsList(Integer orderId) {

        LOGGER.debug("Item DAO: clear items list item {}", orderId);

        MapSqlParameterSource parameters = new MapSqlParameterSource(ORDER_ID, orderId);
        namedParameterJdbcTemplate.update(deleteItemsFromOrder, parameters);
    }
}
