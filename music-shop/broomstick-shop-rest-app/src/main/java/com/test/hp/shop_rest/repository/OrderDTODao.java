package com.test.hp.shop_rest.repository;

import com.test.hp.shop_rest.model.OrderDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 *Order DTO DAO interface
 */
@Component
public interface OrderDTODao {
    /**
     * Find all orderDTOs
     * @return List OrderDTOs
     */
    List<OrderDTO> findAll();

    /**
     * Find orders DTOs by dates
     *
     * @param from Date from
     * @param to Date to
     * @return List OrderDTOs
     */
    List<OrderDTO> findOrdersByDates(LocalDate from, LocalDate to);
}
