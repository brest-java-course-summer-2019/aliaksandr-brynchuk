package com.epam.brest.summer.courses2019;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


public class OrderDaoJdbcImpl implements OrderDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${order.findById}")
    private String findById;


    @Value("${order.addOrder}")
    private String addOrder;

    @Value("${order.deleteOrder}")
    private String deleteOrder;

    private final static String ORDER_ID = "orderId";
    private final static String ORDER_DATE = "orderDate";


    public OrderDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Order findOrderById(Integer orderId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource(ORDER_ID, orderId);
        Order order = namedParameterJdbcTemplate.queryForObject(findById, parameters, BeanPropertyRowMapper.newInstance(Order.class));
        return order;
    }


    @Override
    public Order addOrder(Order order) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(ORDER_DATE, order.getOrderDate());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(addOrder, parameters, generatedKeyHolder);
        order.setOrderId(generatedKeyHolder.getKey().intValue());

        return order;
    }

    @Override
    public void deleteOrder(Integer orderId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(ORDER_ID, orderId);

        namedParameterJdbcTemplate.update(deleteOrder, parameters);
    }
}
