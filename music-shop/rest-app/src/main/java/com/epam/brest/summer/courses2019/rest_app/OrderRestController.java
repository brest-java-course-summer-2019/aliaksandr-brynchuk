package com.epam.brest.summer.courses2019.rest_app;

import com.epam.brest.summer.courses2019.model.Order;
import com.epam.brest.summer.courses2019.model.OrderDTO;
import com.epam.brest.summer.courses2019.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Item REST controller
 */
@RestController
@RequestMapping("/order")
public class OrderRestController {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderRestController.class);

    /**
     * Order service bean
     */
    private OrderService orderService;

    /**
     * Constructor, injection order service bean
     * @param orderService Item Service
     */
    @Autowired
    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Find all orderDTOs
     *
     * @return OrderDTOs List
     */
    @GetMapping(value = "/orders")
    public List<OrderDTO> findAllOrderDTOs(){
        LOGGER.debug("OrderRestController: findAllOrderDTOs");

        return orderService.findAllOrderDTOs();
    }

    /**
     * Find order by ID
     *
     * @param id Order ID
     * @return Order
     */
    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Order findOrderById(@PathVariable Integer id){
        LOGGER.debug("OrderRestController: findOrderById( {id})", id);

        return orderService.findOrderById(id);
    }

    /**
     * Add order
     *
     * @param order Order
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addOrder(@RequestBody Order order){
        LOGGER.debug("OrderRestController: addOrder({order})", order);

        orderService.addOrder(order);
    }

    /**
     * Update order
     *
     * @param order Order
     */
    @PutMapping
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateOrder(@RequestBody Order order){
        LOGGER.debug("OrderRestController: updateOrder({order})", order);

        orderService.updateOrder(order);
    }

    /**
     * Delete order
     *
     * @param id Order ID
     */
    @DeleteMapping(value = "/{id}/delete")
    public void deleteOrder(@PathVariable Integer id){
        LOGGER.debug("OrderRestController: deleteOrder({id})", id);

        orderService.deleteOrder(id);
    }

    /**
     * Find orderDTOs by dates
     *
     * @param dateFrom Date from
     * @param dateTo Date to
     * @return OrderDTOs List
     */
    @GetMapping(value = "/orders/{from}/{to}")
    public List<OrderDTO> findOrdersByDates(@PathVariable("from") String dateFrom, @PathVariable("to") String dateTo) {
        LOGGER.debug("OrderRestController: findOrdersByDates({} - {})", dateFrom, dateTo);

        LocalDate from = LocalDate.parse(dateFrom);
        LocalDate to = LocalDate.parse(dateTo);

        return orderService.findOrdersByDates(from, to);
    }
}
