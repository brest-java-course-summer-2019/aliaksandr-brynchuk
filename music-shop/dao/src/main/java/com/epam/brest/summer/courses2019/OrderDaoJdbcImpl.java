package com.epam.brest.summer.courses2019;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
public class OrderDaoJdbcImpl implements OrderDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String SELECT_ALL = "select o.order_id, o.order_number from orders o order by order_number";

    private static final String ADD_ORDER = "insert into orders (order_number, order_date) values (:order_number, :order_date)";

    private static final String UPDATE_ORDER ="";

    private static final String DELETE_ORDER = "delete from orders where order_id = :order_id";

    public OrderDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public Order addOrder(Order order) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("order_number", order.getOrderNumber());
        parameters.addValue("order_date", order.getOrderDate());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(ADD_ORDER, parameters, generatedKeyHolder);
        order.setOrderId(generatedKeyHolder.getKey().intValue());

        return order;
    }

    @Override
    public void updateOrder(Order order) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();

        for (Item item: order.getItemsList()){
            parameters.addValue("order_id", order.getOrderId());
            namedParameterJdbcTemplate.update(UPDATE_ORDER, parameters);
        }
    }

    @Override
    public void deleteOrder(Integer orderId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("order_id", orderId);

        namedParameterJdbcTemplate.update(DELETE_ORDER, parameters);
    }

    @Override
    public List<Order> findAllOrders() {
        List<Order> orders = namedParameterJdbcTemplate.query(SELECT_ALL, new OrderRowMapper());
        return orders;
    }

    @Override
    public List<Order> findOrdersByDates() {
        return null;
    }

    private class OrderRowMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet resultSet, int i) throws SQLException {
            Order order = new Order();
            order.setOrderId(resultSet.getInt("order_id"));
            order.setOrderNumber(resultSet.getInt("order_number"));
            order.setOrderDate(resultSet.getDate("order_date"));
            return order;
        }
    }

}
