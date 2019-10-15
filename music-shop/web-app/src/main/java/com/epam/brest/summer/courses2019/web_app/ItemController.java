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

import javax.validation.Valid;

@Controller
@RequestMapping("/outer/item")
public class ItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    private ItemService itemService;

    private ItemValidator validator;

    @Autowired
    public ItemController(ItemService itemService, ItemValidator validator) {
        this.itemService = itemService;
        this.validator = validator;
    }

    @GetMapping(value="/assortment")
    public final String allItems(Model model){
        LOGGER.debug("ItemController: find all items({})", itemService);

        model.addAttribute("assortment", itemService.findAllItems());
        return "assortment";
    }

    @GetMapping
    public final String goToAddItemPage(Model model){
        LOGGER.debug("ItemController: gotoAddItemPage({})", model);
        Item item = new Item();
        model.addAttribute("isNew", true);
        model.addAttribute("item", item);
        return "item";
    }

    @PostMapping
    public final String addItem(@Valid Item item, BindingResult result){
        LOGGER.debug("ItemController: add item ({}, {}, {})", item, itemService, result);

        validator.validate(item, result);
        if(result.hasErrors()){
            return "item";
        }else{
            this.itemService.addItem(item);
            return "redirect:/outer/item/assortment";
        }
    }

    @GetMapping(value = "/{id}")
    public final String goToEditItemPage(@PathVariable Integer id, Model model){
        LOGGER.debug("ItemController: edit item ({}, {}, {})", id, itemService, model);

        Item item = itemService.findItemById(id);
        model.addAttribute("isNew", false);
        model.addAttribute("item", item);
        return "item";
    }

    @PostMapping(value = "/{id}")
    public final String updateItem(@Valid Item item, BindingResult result){
        LOGGER.debug("ItemController: update item, ({}, {}, {})", item, itemService, result);

        validator.validate(item, result);
        if(result.hasErrors()){
            return "item";
        }else {
            this.itemService.updateItem(item);
            return "redirect:/outer/item/assortment";
        }
    }

    @GetMapping(value = "/{id}/delete")
    public final String deleteItem(@PathVariable Integer id){
        LOGGER.debug("ItemController: delete item, ({}, {})", id, itemService);

        this.itemService.deleteItem(id);
        return "redirect:/outer/item/assortment";
    }
}
