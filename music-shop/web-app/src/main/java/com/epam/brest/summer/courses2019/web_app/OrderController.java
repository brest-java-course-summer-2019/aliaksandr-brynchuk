package com.epam.brest.summer.courses2019.web_app;

import com.epam.brest.summer.courses2019.Item;
import com.epam.brest.summer.courses2019.ItemService;
import com.epam.brest.summer.courses2019.Order;
import com.epam.brest.summer.courses2019.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class OrderController {

    @Autowired
    OrderService orderService;
    ItemService itemService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @GetMapping(value = "/orders")
    public final String orders(Model model){
        model.addAttribute("orders", orderService.findAllOrderDTOs());
        return "orders";
    }

    @GetMapping(value = "/order/{id}/delete")
    public final String deleteOrder(@PathVariable Integer id){
        LOGGER.debug("delete order {}",  id);

        this.orderService.deleteOrder(id);
        return "redirect:/orders";
    }

    @GetMapping(value = "/order")
    public final String goToAddOrderPage(Model model){
        LOGGER.debug("go to add order page {}", model);

        Order order = new Order();
        model.addAttribute("isNew", true);
        model.addAttribute("order", order);
        return "order";
    }

    @PostMapping(value = "/order")
    public final String addOrder(Order order){
        LOGGER.debug("add order {}", order);

        this.orderService.addOrder(order);
        return "redirect:/orders";
    }

    @GetMapping(value = "/order/{id}")
    public final String goToEditOrderPage(@PathVariable Integer id, Model model){
        LOGGER.debug("goto edit order page {}, {}", id, model);

        Order order = orderService.findOrderById(id);
        List<Item> items = order.getItemsList();
        model.addAttribute("order", order);
        model.addAttribute("items", items);
        return "order";
    }

    @PostMapping(value = "/order/{id}")
    public final String updateOrder(Order order){
        LOGGER.debug("update order {}", order);

        this.orderService.updateOrder(order);
        return "redirect:/orders";
    }
}