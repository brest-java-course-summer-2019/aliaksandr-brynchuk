package com.epam.brest.summer.courses2019;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
public class OrderDaoJdbcImpl implements OrderDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String SELECT_ALL = "select o.order_id, o.order_number, o.order_date from orders o order by order_number";

    private static final String SELECT_ALL_WITH_ORDER_COST = "select o.order_id, o.order_number, o.order_date, "
                                                            +"sum(i.item_price) as orderCost from orders o."
                                                            +"left join items i on o.orders";

    private static final String FIND_BY_DATE ="";

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
        parameters.addValue("order_id", order.getOrderId());

        for (Item item: order.getItemsList()){
            parameters.addValue("item_id", item.getItemId());
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
        List<Order> orders = namedParameterJdbcTemplate.query(SELECT_ALL, BeanPropertyRowMapper.newInstance(Order.class));
        return orders;
    }

    @Override
    public List<Order> findOrdersByDates(Date from, Date to) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
         parameters.addValue("date_from", from);
         parameters.addValue("date_to", to);

         List<Order> orders = namedParameterJdbcTemplate.query(FIND_BY_DATE, parameters, BeanPropertyRowMapper.newInstance(Order.class));

        return orders;
    }


}
