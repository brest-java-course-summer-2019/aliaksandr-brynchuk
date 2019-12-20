package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * Order DAO implementation, gets data from data base
 */
@Repository
@PropertySource("classpath:/sql.properties")
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
     * Find Order by id
     *
     * @param orderId Order Id
     * @return Order
     */
    @Override
    public Order findOrderById(Integer orderId) {
        LOGGER.debug("Order DAO: find order by id({})", orderId);

        MapSqlParameterSource parameters = new MapSqlParameterSource(ORDER_ID, orderId);
        return namedParameterJdbcTemplate.queryForObject(findById, parameters, BeanPropertyRowMapper.newInstance(Order.class));
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
}
