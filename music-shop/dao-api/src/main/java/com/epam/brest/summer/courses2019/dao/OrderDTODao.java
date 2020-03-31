package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.OrderDTO;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

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
    Flux<OrderDTO> findAll();

    /**
     * Find orders DTOs by dates
     *
     * @param from Date from
     * @param to Date to
     * @return List OrderDTOs
     */
    Flux<OrderDTO> findOrdersByDates(LocalDate from, LocalDate to);
}
