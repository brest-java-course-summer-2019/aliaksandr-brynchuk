package com.epam.brest.summer.courses2019.services;

import com.epam.brest.summer.courses2019.model.Item;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Item service interface
 */
public interface ItemService {
    /**
     * Add item
     * @param item Item
     */
    Mono<Void> addItem(Item item);

    /**
     * Update item
     *
     * @param item Item
     */
    Mono<Void> updateItem(Item item);

    /**
     * Delete item
     *
     * @param itemId Item Id
     */
    Mono<Void> deleteItem(Integer itemId);

    /**
     * Find all items
     *
     * @return Items list
     */
    Flux<Item> findAllItems();

    /**
     * Find item by id
     *
     * @param itemId Item Id
     * @return Item
     */
    Mono<Item> findItemById(Integer itemId);
}
