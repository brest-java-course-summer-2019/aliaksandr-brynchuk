package com.epam.brest.summer.courses2019.web_app;

import com.epam.brest.summer.courses2019.Item;
import com.epam.brest.summer.courses2019.ItemService;
import com.epam.brest.summer.courses2019.Order;
import com.epam.brest.summer.courses2019.OrderService;

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


@Controller
@RequestMapping("outer/order")
public class OrderController {

    private OrderService orderService;

    private ItemService itemService;

    private OrderValidator validator;

    @Autowired
    public OrderController(OrderService orderService, ItemService itemService, OrderValidator validator) {
        this.orderService = orderService;
        this.itemService = itemService;
        this.validator = validator;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @GetMapping(value = "/orders")
    public final String orders(Model model){
        LOGGER.debug("OrderController: find all orders()");

        model.addAttribute("orders", orderService.findAllOrderDTOs());
        return "orders";
    }

    @GetMapping(value = "/{id}/delete")
    public final String deleteOrder(@PathVariable Integer id){
        LOGGER.debug("OrderController: delete order {}",  id);

        this.orderService.deleteOrder(id);
        return "redirect:/outer/order/orders";
    }

    @GetMapping
    public final String goToAddOrderPage(Model model){
        LOGGER.debug("OrderController: go to add order page");

        Order order = new Order();
        List<Item> items = itemService.findAllItems();
        model.addAttribute("isNew", true);
        model.addAttribute("order", order);
        model.addAttribute("items", items);
        return "order";
    }

    @PostMapping
    public final String addOrder(@Valid Order order, BindingResult result) {
        LOGGER.debug("OrderController: add order {}, {}", order, result);

        validator.validate(order, result);
        if (result.hasErrors()) {
            return "order";
        } else {
            this.orderService.addOrder(order);
            return "redirect:/outer/order/orders";
        }
    }

    @GetMapping(value = "/{id}")
    public final String goToEditOrderPage(@PathVariable Integer id, Model model){
        LOGGER.debug("OrderController: goto edit order page {}, {}", id, model);

        Order order = orderService.findOrderById(id);
        List<Item> items = Stream.of(itemService.findAllItems(), order.getItemsList()).
                flatMap(Collection::stream)
                .collect(Collectors.toList());
        model.addAttribute("order", order);
        model.addAttribute("items", items);
        return "order";
    }

    @PostMapping(value = "/{id}")
    public final String updateOrder(@Valid Order order, BindingResult result) {
        LOGGER.debug("OrderController: update order {}, {}", order, result);

        validator.validate(order, result);
        if (result.hasErrors()) {
            return "order";
        } else {
            this.orderService.updateOrder(order);
            return "redirect:/outer/order/orders";
        }
    }

    @GetMapping(value = "/orderview/{id}")
    public final String orderView(@PathVariable Integer id, Model model){
        LOGGER.debug("OrderController: goto order page {}", id);

        Order order = orderService.findOrderById(id);
        List<Item> items = order.getItemsList();

        model.addAttribute("order", order);
        model.addAttribute("items", items);
        return "orderview";
    }

    @GetMapping(value = "/orders/{from}/{to}")
    public String filterByDates(@PathVariable("from") String dateFrom, @PathVariable("to") String dateTo, Model model) {
        LOGGER.debug("OrderController: filterByDates({} - {})", dateFrom, dateTo);

        LocalDate from = LocalDate.parse(dateFrom);
        LocalDate to = LocalDate.parse(dateTo);

        model.addAttribute("orders", orderService.findOrdersByDates(from, to));
        return "orders";
    }
}
