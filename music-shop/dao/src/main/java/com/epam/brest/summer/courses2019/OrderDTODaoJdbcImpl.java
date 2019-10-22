package com.epam.brest.summer.courses2019;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class OrderDTODaoJdbcImpl implements OrderDTODao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${orderDto.findAll}")
    private String findAll;

    @Value("${orderDto.findByDates}")
    private String findByDates;

    private final static String DATE_FROM = "dateFrom";
    private final static String DATE_TO = "dateTo";

    public OrderDTODaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<OrderDTO> findAllOrderDTOs() {
        return namedParameterJdbcTemplate.query(findAll, BeanPropertyRowMapper.newInstance(OrderDTO.class));
    }

    @Override
    public List<OrderDTO> findOrdersByDates(LocalDate from, LocalDate to) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(DATE_FROM, from);
        parameters.addValue(DATE_TO, to);

        return namedParameterJdbcTemplate.query(findByDates, parameters, BeanPropertyRowMapper.newInstance(OrderDTO.class));
    }

}
