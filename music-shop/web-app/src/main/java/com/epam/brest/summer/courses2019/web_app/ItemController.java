package com.epam.brest.summer.courses2019.web_app;

import com.epam.brest.summer.courses2019.Item;
import com.epam.brest.summer.courses2019.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    ItemService itemService;

    @GetMapping(value="/assortment")
    public final String allItems(Model model){
        LOGGER.debug("Find all items{()}", model);
        model.addAttribute("assortment", itemService.findAllItems());
        return "assortment";
    }

    @GetMapping(value = "/item")
    public final String goToAddItemPage(Model model){
        LOGGER.debug("gotoAddItemPage({})", model);
        Item item = new Item();
        model.addAttribute("isNew", true);
        model.addAttribute("item", item);
        return "item";
    }

    @PostMapping(value = "/item")
    public  final String addItem(Item item){
        LOGGER.debug("add item ({})", item);
        this.itemService.addItem(item);
        return "redirect:/assortment";
    }

    @GetMapping(value = "/item/{id}")
    public final String goToEditItemPage(@PathVariable Integer id, Model model){
        LOGGER.debug("Edit item ({}, {})", id, model);

        Optional<Item> item = itemService.findItemById(id);
        model.addAttribute("item", item);
        return "item";
    }

    @PostMapping(value = "/item/{id}")
    public final String updateItem(Item item){
        LOGGER.debug("update item, ({})", item);

        this.itemService.updateItem(item);
        return "redirect:/assortment";
    }

    @GetMapping(value = "/item/{id}")
    public final String deleteItem(@PathVariable Integer id){
        LOGGER.debug("delete item, ({})", id);

        this.itemService.deleteItem(id);
        return "redirect:/assortment";
    }










}
