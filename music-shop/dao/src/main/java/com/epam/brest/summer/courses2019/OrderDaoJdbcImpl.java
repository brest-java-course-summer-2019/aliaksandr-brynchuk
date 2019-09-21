package com.epam.brest.summer.courses2019;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.time.LocalDate;
import java.util.List;


public class OrderDaoJdbcImpl implements OrderDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    private static final String SELECT_ALL = "select o.order_id,/* o.order_number,*/ o.order_date from orders o order by order_number";

    private static final String SELECT_BY_ID = "select o.order_id, /*o.order_number,*/ o.order_date, sum(i.item_price) as orderCost " +
                                               "from orders o " +
                                               "left join order_items io on o.order_id = io.order_id " +
                                               "left join items i on i.item_id = io.item_id " +
                                               "where o.order_id = :order_id " +
                                               "group by o.order_id";


    private static final String FIND_BY_DATE = "";


    private static final String ADD_ORDER = "insert into orders (/*order_number,*/ order_date) values (/* :order_number,*/ :order_date)";

    private static final String DELETE_ORDER = "delete from orders where order_id = :order_id";


    private static final String ORDER_NUMBER = "order_number";

    public OrderDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Order findOrderById(Integer orderId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource("order_id", orderId);
        Order order = namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID, parameters, BeanPropertyRowMapper.newInstance(Order.class));
        return order;
    }


    @Override
    public List<Order> findOrdersByDates(LocalDate from, LocalDate to) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("date_from", from);
        parameters.addValue("date_to", to);

        List<Order> orders = namedParameterJdbcTemplate.query(FIND_BY_DATE, parameters, BeanPropertyRowMapper.newInstance(Order.class));
        return orders;
    }

    @Override
    public Order addOrder(Order order) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue(ORDER_NUMBER, order.getOrderNumber());
        parameters.addValue("order_date", order.getOrderDate());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(ADD_ORDER, parameters, generatedKeyHolder);
        order.setOrderId(generatedKeyHolder.getKey().intValue());

        return order;
    }


    @Override
    public void deleteOrder(Integer orderId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("order_id", orderId);

        namedParameterJdbcTemplate.update(DELETE_ORDER, parameters);
    }
}
