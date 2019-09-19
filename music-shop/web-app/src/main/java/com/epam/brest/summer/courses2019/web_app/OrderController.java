package com.epam.brest.summer.courses2019.web_app;

import com.epam.brest.summer.courses2019.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

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
}
