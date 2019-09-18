package com.epam.brest.summer.courses2019.web_app;

import com.epam.brest.summer.courses2019.OrderService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    OrderService orderService;

    @GetMapping(value = "/orders")
    public final String orders(Model model){
        model.addAttribute("orders", orderService.findAllOrders());
        return "orders";
    }
}
