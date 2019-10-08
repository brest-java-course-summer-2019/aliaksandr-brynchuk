package com.epam.brest.summer.courses2019.web_app;

import com.epam.brest.summer.courses2019.Item;
import com.epam.brest.summer.courses2019.ItemService;
import com.epam.brest.summer.courses2019.web_app.validators.ItemValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import javax.validation.Valid;

@Controller
public class ItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    ItemService itemService;

    @Autowired
    ItemValidator validator;

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
    public final String addItem(@Valid Item item, BindingResult result){
        LOGGER.debug("add item ({}, {})", item, result);

        validator.validate(item, result);
        if(result.hasErrors()){
            return "item";
        }else{
            this.itemService.addItem(item);
            return "redirect:/assortment";
        }
    }

    @GetMapping(value = "/item/{id}")
    public final String goToEditItemPage(@PathVariable Integer id, Model model){
        LOGGER.debug("Edit item ({}, {})", id, model);

        Optional<Item> item = itemService.findItemById(id);
        model.addAttribute("isNew", false);
        model.addAttribute("item", item);
        return "item";
    }

    @PostMapping(value = "/item/{id}")
    public final String updateItem(@Valid Item item, BindingResult result){
        LOGGER.debug("update item, ({}, {})", item, result);

        validator.validate(item, result);
        if(result.hasErrors()){
            return "item";
        }else {
            this.itemService.updateItem(item);
            return "redirect:/assortment";
        }
    }

    @GetMapping(value = "/item/{id}/delete")
    public final String deleteItem(@PathVariable Integer id){
        LOGGER.debug("delete item, ({})", id);

        this.itemService.deleteItem(id);
        return "redirect:/assortment";
    }

}
