package com.test.service;

import com.test.react.model.Item;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Service {

    Mono<Item> getItem(Integer id);
    Flux<Item> getItems();
    Mono<Item> createItem(Item item);
}
