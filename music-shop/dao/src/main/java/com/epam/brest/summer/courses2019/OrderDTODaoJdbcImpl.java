package com.epam.brest.summer.courses2019;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;


public class OrderDTODaoJdbcImpl implements OrderDTODao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String SELECT_ALL_WITH_COST = "select o.order_id, o.order_date, sum(i.item_price) as orderCost " +
            "from orders o " +
            "left join order_items io on o.order_id = io.order_id " +
            "left join items i on i.item_id = io.item_id "+
            "group by o.order_id";

    public OrderDTODaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<OrderDTO> findAllOrderDTOs() {
        List<OrderDTO>orders = namedParameterJdbcTemplate.query(SELECT_ALL_WITH_COST, BeanPropertyRowMapper.newInstance(OrderDTO.class));
        return orders;
    }
}
