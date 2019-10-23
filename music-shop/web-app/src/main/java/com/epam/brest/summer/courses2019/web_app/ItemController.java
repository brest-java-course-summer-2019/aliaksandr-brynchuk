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

/**
 * Item controller
 */
@Controller
@RequestMapping("/outer/item")
public class ItemController {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    /**
     * item service
     */
    private ItemService itemService;

    /**
     * validator
     */
    private ItemValidator validator;

    /**
     * Constructor, inject item service and item validator beans
     *
     * @param itemService item service
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
    @GetMapping(value="/assortment")
    public final String allItems(Model model){
        LOGGER.debug("ItemController: find all items({})", itemService);

        model.addAttribute("assortment", itemService.findAllItems());
        return "assortment";
    }

    /**
     * Go to the add-item page
     *
     * @param model Item and isNew flag
     * @return item template
     */
    @GetMapping
    public final String goToAddItemPage(Model model){
        LOGGER.debug("ItemController: gotoAddItemPage()");
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
    @PostMapping
    public final String addItem(@Valid Item item, BindingResult result, Model model){
        LOGGER.debug("ItemController: add item ({}, {})", item, result);

        validator.validate(item, result);
        if(result.hasErrors()){
            model.addAttribute("isNew", true);
            return "item";
        }else{
            this.itemService.addItem(item);
            return "redirect:/outer/item/assortment";
        }
    }

    /**
     * Go to edit-item page
     *
     * @param id Item ID
     * @param model "isNew" and Item
     * @return item template
     */
    @GetMapping(value = "/{id}")
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
    @PostMapping(value = "/{id}")
    public final String updateItem(@Valid Item item, BindingResult result){
        LOGGER.debug("ItemController: update item, ({}, {})", item, result);

        validator.validate(item, result);
        if(result.hasErrors()){
            return "item";
        }else {
            this.itemService.updateItem(item);
            return "redirect:/outer/item/assortment";
        }
    }

    /**
     * Delete item
     *
     * @param id Item ID
     * @return redirect to assortment page
     */
    @GetMapping(value = "/{id}/delete")
    public final String deleteItem(@PathVariable Integer id){
        LOGGER.debug("ItemController: delete item, ({})", id);

        this.itemService.deleteItem(id);
        return "redirect:/outer/item/assortment";
    }
}
