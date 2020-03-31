package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Item;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@Qualifier("itemRepository")
public interface ItemRepository extends ReactiveCrudRepository<Item, Integer>, ItemDao {

    @Override
    @Modifying
    Mono<Void> saveItem(Item item);

    @Override
    @Modifying
    Mono<Void> deleteByItemId(Integer itemId);

    @Override
    @Query(name = "getNotReservedItems")
    Flux<Item> findAll();

    @Override
    Mono<Item> findByItemId(Integer itemId);
}
