package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.dao.OrderDTODao;
import com.epam.brest.summer.courses2019.model.OrderDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.List;

@Repository
@Qualifier("orderDtoRepository")
public interface OrderDtoRepository extends OrderDTODao, ReactiveCrudRepository<OrderDTO, Integer> {

    @Override
    @Query(name = "getOrdersDTOWithCost")
    Flux<OrderDTO> findAll();

    @Override
    @Query(name = "getOrdersDTOWithCostByDates")
    Flux<OrderDTO> findOrdersByDates(@Param("from") LocalDate from, @Param("to") LocalDate to);
}
