package com.test.hp.client.controllers;

import com.test.hp.client.validators.ItemValidator;
import com.test.hp.client.api.ItemService;
import com.test.hp.client.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Item controller
 */
@Controller
@RequestMapping("/items")
public class ItemController {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    /**
     * item com.test.hp.service
     */
    private ItemService itemService;

    /**
     * validator
     */
    private ItemValidator validator;

    /**
     * Constructor, injection item com.test.hp.service and item validator beans
     *
     * @param itemService item com.test.hp.service
     * @param validator item validator
     */
    @Autowired
    public ItemController(ItemService itemService, ItemValidator validator) {
        this.itemService = itemService;
        this.validator = validator;
    }

    /**
     * Find all items for assortment
     * assortment - all non-in-order items
     *
     * @param model Model
     * @return assortment template
     */
    @GetMapping
    public final String allItems(Model model){
        LOGGER.debug("ItemController: find all items");

        model.addAttribute("assortment", itemService.findAllItems());
        return "assortment";
    }

    /**
     * Go to the add-item page
     *
     * @param model Item and isNew flag
     * @return item template
     */
    @GetMapping("/new")
    public final String goToAddItemPage(Model model){
        LOGGER.debug("ItemController: gotoAddItemPage");
        Item item = new Item();
        model.addAttribute("isNew", true);
        model.addAttribute("item", item);
        return "item";
    }

    /**
     * Add item
     *
     * @param item Item
     * @param result Binding result
     * @param model flag "isNew" was added for resolve bug with updateItem endpoint call
     *              after entering incorrect data
     * @return redirect to assortment page
     */
    @PostMapping("/new")
    public final String addItem(@Valid Item item, BindingResult result, Model model){
        LOGGER.debug("ItemController: add item ({}, {})", item, result);

        validator.validate(item, result);
        if(result.hasErrors()){
            model.addAttribute("isNew", true);
            return "item";
        }else{
            this.itemService.addItem(item);
            return "redirect:/items";
        }
    }

    /**
     * Go to edit-item page
     *
     * @param id Item ID
     * @param model "isNew" and Item
     * @return item template
     */
    @GetMapping(value = "/edit/{id}")
    public final String goToEditItemPage(@PathVariable Integer id, Model model){
        LOGGER.debug("ItemController: edit item ({})", id);

        Item item = itemService.findItemById(id);
        model.addAttribute("isNew", false);
        model.addAttribute("item", item);
        return "item";
    }

    /**
     * Update item
     *
     * @param item Item
     * @param result Binding result
     * @return item template if incorrect data entered, or redirect to assortment page if data correct
     */
    @PostMapping(value = "/edit/{id}")
    public final String updateItem(@Valid Item item, BindingResult result){
        LOGGER.debug("ItemController: update item, ({}, {})", item, result);

        validator.validate(item, result);
        if(result.hasErrors()){
            return "item";
        }else {
            this.itemService.updateItem(item);
            return "redirect:/items";
        }
    }

    /**
     * Delete item
     *
     * @param id Item ID
     * @return redirect to assortment page
     */
    @GetMapping(value = "/{id}")
    public final String deleteItem(@PathVariable Integer id){
        LOGGER.debug("ItemController: delete item, ({})", id);

        this.itemService.deleteItem(id);
        return "redirect:/items";
    }
}
