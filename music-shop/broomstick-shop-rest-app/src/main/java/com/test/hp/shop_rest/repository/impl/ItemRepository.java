package com.test.hp.shop_rest.repository.impl;

import com.test.hp.shop_rest.model.Item;
import com.test.hp.shop_rest.repository.ItemDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("itemRepository")
public interface ItemRepository extends CrudRepository<Item, Integer>, ItemDao {

    @Override
    @Modifying
    default Item addItem(Item item) {
        save(item);
        return item;
    }

    @Override
    @Modifying
    default void updateItem(Item item) {
        save(item);
    }

    @Override
    @Modifying
    @Query("delete from Item i where i.itemId = :id")
    void deleteItem(@Param("id") Integer itemId);

    @Override
    @Query(name = "getNotReservedItems")
    List<Item> findAll();

    @Override
    Item findByItemId(Integer itemId);
}
