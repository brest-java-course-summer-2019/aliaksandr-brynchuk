package com.test.service;

import com.test.react.model.Item;
import com.test.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceImpl implements Service {

    private ItemRepository repository;


    @Override
    public Mono<Item> getItem(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Item> getItems() {
        return repository.findAll();
    }

    @Override
    public Mono<Item> createItem(Item item) {
        return repository.save(item);
    }
}
