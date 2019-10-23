package com.epam.brest.summer.courses2019;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * Order DTO DAO implementation, gets data from data base
 */
@Component
public class OrderDTODaoJdbcImpl implements OrderDTODao {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDaoJdbcImpl.class);

    /**
     * NamedParameterJdbcTemplate field
     */
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     *SQL from property file. Find all orderDTOS
     */
    @Value("${orderDto.findAll}")
    private String findAll;

    /**
     *SQL from property file. Find orderDTOS by dates
     */
    @Value("${orderDto.findByDates}")
    private String findByDates;

    /**
     * Constant fields
     */
    private final static String DATE_FROM = "dateFrom";
    private final static String DATE_TO = "dateTo";

    /**
     * Constructor, inject namedParameterJdbcTemplate bean
     *
     * @param namedParameterJdbcTemplate namedParameterJdbcTemplate bean
     */
    public OrderDTODaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Find all orderDTOs
     *
     * @return OrderDTOs List
     */
    @Override
    public List<OrderDTO> findAllOrderDTOs() {
        return namedParameterJdbcTemplate.query(findAll, BeanPropertyRowMapper.newInstance(OrderDTO.class));
    }

    /**
     * Find orderDTOs by dates
     *
     * @param from Date from
     * @param to Date to
     * @return OrderDTOs List
     */
    @Override
    public List<OrderDTO> findOrdersByDates(LocalDate from, LocalDate to) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(DATE_FROM, from);
        parameters.addValue(DATE_TO, to);

        return namedParameterJdbcTemplate.query(findByDates, parameters, BeanPropertyRowMapper.newInstance(OrderDTO.class));
    }

}
