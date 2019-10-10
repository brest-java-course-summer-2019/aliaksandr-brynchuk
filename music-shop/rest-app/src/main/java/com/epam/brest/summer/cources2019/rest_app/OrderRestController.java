package com.epam.brest.summer.cources2019.rest_app;

import com.epam.brest.summer.courses2019.Order;
import com.epam.brest.summer.courses2019.OrderDTO;
import com.epam.brest.summer.courses2019.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class OrderRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderRestController.class);

    @Autowired
    OrderService orderService;

    @GetMapping(value = "/orders")
    public List<OrderDTO> findAllOrderDTOs(){
        LOGGER.debug("OrderRestController: findAllOrderDTOs");

        return orderService.findAllOrderDTOs();
    }

    @GetMapping(value = "/order/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Order findOrderById(@PathVariable Integer id){
        LOGGER.debug("OrderRestController: findOrderById({id})", id);

        return orderService.findOrderById(id);
    }

    @PostMapping(value = "/order")
    public void addOrder(@RequestBody Order order){
        LOGGER.debug("OrderRestController: addOrder({order})", order);

        orderService.addOrder(order);
    }

    @PutMapping(value = "/order/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateOrder(@RequestBody Order order){
        LOGGER.debug("OrderRestController: updateOrder({order})", order);

        orderService.updateOrder(order);
    }

    @DeleteMapping(value = "order/{id}")
    public void deleteOrder(@PathVariable Integer id){
        LOGGER.debug("OrderRestController: deleteOrder({id})", id);

        orderService.deleteOrder(id);
    }

    @GetMapping(value = "/orders")
    public List<OrderDTO> findOrderDTOsByDates(LocalDate from, LocalDate to){
        LOGGER.debug("OrderRestController: findOrderDTOsByDates");

        return orderService.findOrdersByDates(from, to);
    }

}
