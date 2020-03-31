package com.epam.brest.summer.courses2019.rest_app;

import com.epam.brest.summer.courses2019.model.Item;
import com.epam.brest.summer.courses2019.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Item REST controller
 */

@RestController
@RequestMapping("/items")
public class ItemRestController {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemRestController.class);

    /**
     * item service field
     */
    private ItemService itemService;

    /**
     * Constructor, injection item service bean
     * @param itemService Item Service
     */

    @Autowired
    public ItemRestController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * Find all items
     *
     * @return Items list
     */
    @GetMapping
    public Flux<Item> findAllItems(){
        LOGGER.debug("ItemRestController: findAllItems");

        return itemService.findAllItems();
    }

    /**
     * find item by id
     *
     * @param itemId Item ID
     * @return Item
     */
    @GetMapping(value = "/{itemId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<Item> findItemById(@PathVariable Integer itemId){
        LOGGER.debug("ItemRestController: findItemById({})", itemId);

        return itemService.findItemById(itemId);
    }

    /**
     * Add item
     *
     * @param item Item
     */
    @PostMapping
    public Mono<Void> addItem(@RequestBody Item item){
        LOGGER.debug("ItemRestController: addItem({})", item);

        return itemService.addItem(item);
    }

    /**
     * Update item
     *
     * @param item Item
     */
    @PutMapping("/{itemId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Mono<Void> updateItem(@RequestBody Item item){
        LOGGER.debug("ItemRestController: updateItem({})", item);

        return itemService.updateItem(item);
    }

    /**
     * Delete item
     *
     * @param itemId Item ID
     */
    @DeleteMapping(value = "/{itemId}")
    public Mono<Void> deleteItem(@PathVariable Integer itemId){
        LOGGER.debug("ItemRestController: deleteItem({})", itemId);

        return itemService.deleteItem(itemId);
    }
}

