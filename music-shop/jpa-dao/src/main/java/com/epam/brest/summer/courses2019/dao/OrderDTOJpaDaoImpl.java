package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

public class OrderDTOJpaDaoImpl implements OrderDTODao{

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDTOJpaDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<OrderDTO> findAllOrderDTOs() {
        return entityManager.createNamedQuery("getOrdersDTOWithCost", OrderDTO.class).getResultList();
    }

    @Override
    public List<OrderDTO> findOrdersByDates(LocalDate from, LocalDate to) {
        return entityManager.createNamedQuery("getOrdersDTOWithCostByDates", OrderDTO.class)
                .setParameter("from", from).setParameter("to", to).getResultList();
    }
}
