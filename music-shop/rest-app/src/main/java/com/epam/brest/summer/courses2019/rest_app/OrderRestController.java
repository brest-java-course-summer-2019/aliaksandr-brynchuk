package com.epam.brest.summer.courses2019.rest_app;

import com.epam.brest.summer.courses2019.model.Order;
import com.epam.brest.summer.courses2019.model.OrderDTO;
import com.epam.brest.summer.courses2019.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

/**
 * Item REST controller
 */
@RestController
@RequestMapping("/orders")
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
    @GetMapping
    public Flux<OrderDTO> findAllOrderDTOs(){
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
    public Mono<Order> findOrderById(@PathVariable Integer id){
        LOGGER.debug("OrderRestController: findOrderById({})", id);

        return orderService.findOrderById(id);
    }

    /**
     * Add order
     *
     * @param order Order
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<Void> addOrder(@RequestBody Order order){
        LOGGER.debug("OrderRestController: addOrder({})", order);

        return orderService.addOrder(order);
    }

    /**
     * Update order
     *
     * @param order Order
     */
    @PutMapping("/{orderId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Mono<Void> updateOrder(@PathVariable Integer orderId, @RequestBody Order order){
        LOGGER.debug("OrderRestController: updateOrder({})", order);

        return orderService.updateOrder(order);
    }

    /**
     * Delete order
     *
     * @param id Order ID
     */
    @DeleteMapping(value = "/{id}")
    public Mono<Void> deleteOrder(@PathVariable Integer id){
        LOGGER.debug("OrderRestController: deleteOrder({})", id);

        return orderService.deleteOrder(id);
    }

    /**
     * Find orderDTOs by dates
     *
     * @param dateFrom Date from
     * @param dateTo Date to
     * @return OrderDTOs List
     */
    @GetMapping(value = "/{from}/{to}")
    public Flux<OrderDTO> findOrdersByDates(@PathVariable("from") String dateFrom, @PathVariable("to") String dateTo) {
        LOGGER.debug("OrderRestController: findOrdersByDates({} - {})", dateFrom, dateTo);

        LocalDate from = LocalDate.parse(dateFrom);
        LocalDate to = LocalDate.parse(dateTo);

        return orderService.findOrdersByDates(from, to);
    }
}
