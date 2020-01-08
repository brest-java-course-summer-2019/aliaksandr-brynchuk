package com.epam.brest.summer.courses2019.web_app;

import com.epam.brest.summer.courses2019.model.Item;
import com.epam.brest.summer.courses2019.model.Order;
import com.epam.brest.summer.courses2019.services.ItemService;
import com.epam.brest.summer.courses2019.services.OrderService;

import com.epam.brest.summer.courses2019.web_app.validators.OrderValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Order controller
 */
@Controller
@RequestMapping("/orders")
public class OrderController {

    /**
     * Order service
     */
    private OrderService orderService;

    /**
     * Item service
     */
    private ItemService itemService;

    /**
     * Order validator
     */
    private OrderValidator validator;

    /**
     * Constructor, injection orderService, itemService, order validator beans
     *
     * @param orderService Order Service
     * @param itemService Item Service
     * @param validator Order Validator
     */
    @Autowired
    public OrderController(OrderService orderService, ItemService itemService, OrderValidator validator) {
        this.orderService = orderService;
        this.itemService = itemService;
        this.validator = validator;
    }

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    /**
     * Get all orders
     *
     * @param model All orders
     * @return Orders template
     */
    @GetMapping
    public final String orders(Model model){
        LOGGER.debug("OrderController: find all orders()");

        model.addAttribute("orders", orderService.findAllOrderDTOs());
        return "orders";
    }

    /**
     * Delete order
     *
     * @param id Order ID
     * @return redirect to orders template
     */
    @PostMapping(value = "/{id}")
    public final String deleteOrder(@PathVariable Integer id){
        LOGGER.debug("OrderController: delete order {}",  id);

        this.orderService.deleteOrder(id);
        return "redirect:/orders";
    }

    /**
     * Go to add-order page
     *
     * @param model "isNew" flag, order and non-in-order items for create order items list
     * @return order template
     */
    @GetMapping("/new")
    public final String goToAddOrderPage(Model model){
        LOGGER.debug("OrderController: go to add order page");

        Order order = new Order();
        List<Item> items = itemService.findAllItems();
        model.addAttribute("isNew", true);
        model.addAttribute("order", order);
        model.addAttribute("items", items);
        return "order";
    }

    /**
     * Add order
     *
     * @param order Order
     * @param result Binding result
     * @return redirect to orders template
     */

    @PostMapping("/new")
    public final String addOrder(@Valid Order order, BindingResult result, Model model) {
        LOGGER.debug("OrderController: add order {}, {}", order, result);

        validator.validate(order, result);
        if (result.hasErrors()) {
            model.addAttribute("isNew", true);
            return "order";
        } else {
            this.orderService.addOrder(order);
            return "redirect:/orders";
        }
    }

    /**
     * Go to edit-order page
     *
     * @param id Order ID
     * @param model Order and non-in-order items + items from order for update order items list
     * @return order template
     */
    @GetMapping(value = "/edit/{id}")
    public final String goToEditOrderPage(@PathVariable Integer id, Model model){
        LOGGER.debug("OrderController: goto edit order page {}", id);

        Order order = orderService.findOrderById(id);
        List<Item> items = Stream.of(itemService.findAllItems(), order.getItemsList()).
                flatMap(Collection::stream)
                .collect(Collectors.toList());
        model.addAttribute("order", order);
        model.addAttribute("items", items);
        return "order";
    }

    /**
     * Update order
     *
     * @param order Order
     * @param result Binding result
     * @return redirect to orders template
     */
    @PostMapping(value = "/edit/{id}")
    public final String updateOrder(@Valid Order order, BindingResult result) {
        LOGGER.debug("OrderController: update order {}, {}", order, result);

        validator.validate(order, result);
        if (result.hasErrors()) {
            return "order";
        } else {
            this.orderService.updateOrder(order);
            return "redirect:/orders";
        }
    }

    /**
     * Page with order information
     *
     * @param id Order ID
     * @param model Order and order item list
     * @return orderview template
     */
    @GetMapping(value = "/orderview/{id}")
    public final String orderView(@PathVariable Integer id, Model model){
        LOGGER.debug("OrderController: goto order page {}", id);

        Order order = orderService.findOrderById(id);
        List<Item> items = order.getItemsList();

        model.addAttribute("order", order);
        model.addAttribute("items", items);
        return "orderview";
    }

    /**
     * Find orders by dates
     *
     * @param dateFrom Date from
     * @param dateTo Date to
     * @param model orders list
     * @return Order List
     */
    @GetMapping(value = "/{from}/{to}")
    public String filterByDates(@PathVariable("from") String dateFrom, @PathVariable("to") String dateTo, Model model) {
        LOGGER.debug("OrderController: filterByDates({} - {})", dateFrom, dateTo);

        LocalDate from = LocalDate.parse(dateFrom);
        LocalDate to = LocalDate.parse(dateTo);

        model.addAttribute("orders", orderService.findOrdersByDates(from, to));
        return "orders";
    }
}
