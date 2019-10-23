package com.epam.brest.summer.courses2019;

import java.time.LocalDate;
import java.util.List;

/**
 *Order DTO DAO interface
 */
public interface OrderDTODao {
    /**
     * Find all orderDTOs
     * @return List OrderDTOs
     */
    List<OrderDTO> findAllOrderDTOs();

    /**
     * Find orders DTOs by dates
     *
     * @param from Date from
     * @param to Date to
     * @return List OrderDTOs
     */
    List<OrderDTO> findOrdersByDates(LocalDate from, LocalDate to);
}
