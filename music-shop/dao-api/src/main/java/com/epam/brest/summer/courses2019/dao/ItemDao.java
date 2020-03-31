package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Item;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 *Item DAO interface
 */
public interface ItemDao {

    /**
     * Add item to db
     *
     * @param item Item
     * @return Item
     */
    Mono<Void> saveItem(Item item);

//    /**
//     * Update item
//     *
//     * @param item Item
//     */
//    Mono<Void> updateItem(Item item);

    /**
     * Delete item
     *
     * @param itemId Item Id
     */
    Mono<Void> deleteByItemId(Integer itemId);

    /**
     *Get all items
     *
     * @return Items List
     */
    Flux<Item> findAll();

    /**
     *Find item by id
     *
     * @param itemId Item ID
     * @return Optional<>Item</>
     */
    Mono<Item> findByItemId(Integer itemId);
}
