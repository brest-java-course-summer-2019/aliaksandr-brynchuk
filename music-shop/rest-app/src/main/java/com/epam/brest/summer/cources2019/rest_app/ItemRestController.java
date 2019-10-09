package com.epam.brest.summer.cources2019.rest_app;

import com.epam.brest.summer.courses2019.Item;
import com.epam.brest.summer.courses2019.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemRestController.class);

    @Autowired
    ItemService itemService;

    @GetMapping(value = "/assortment")
    public List<Item> findAllItems(){
        LOGGER.debug("ItemRestConsumer: findAllItems");

        return itemService.findAllItems();
    }

    @GetMapping(value = "/item/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Item findItemById(@PathVariable Integer itemId){
        LOGGER.debug("ItemRestConsumer: findItemById({})", itemId);

        return itemService.findItemById(itemId);
    }

    @PostMapping(value = "/item")
    public void addItem(@RequestBody Item item){
        LOGGER.debug("ItemRestConsumer: addItem({})", item);

        itemService.addItem(item);
    }

    @PutMapping(value = "/item/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateItem(@RequestBody Item item){
        LOGGER.debug("ItemRestConsumer: updateItem({})", item);

        itemService.updateItem(item);
    }

    @DeleteMapping(value = "/item/{itemId}")
    public void deleteItem(@PathVariable Integer itemId){
        LOGGER.debug("ItemRestConsumer: deleteItem({})", itemId);

        itemService.deleteItem(itemId);
    }
}
