package com.test.hp.shop_rest.repository.impl;

import com.test.hp.shop_rest.model.OrderDTO;
import com.test.hp.shop_rest.repository.OrderDTODao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Qualifier("orderDtoRepository")
public interface OrderDtoRepository extends OrderDTODao, CrudRepository<OrderDTO, Integer> {

    @Override
    @Query(name = "getOrdersDTOWithCost")
    List<OrderDTO> findAll();

    @Override
    @Query(name = "getOrdersDTOWithCostByDates")
    List<OrderDTO> findOrdersByDates(@Param("from") LocalDate from, @Param("to") LocalDate to);
}
