package com.epam.brest.summer.courses2019;

import java.time.LocalDate;
import java.util.List;

public interface OrderDTODao {
    List<OrderDTO> findAllOrderDTOs();

    List<Order> findOrdersByDates(LocalDate from, LocalDate to);
}
