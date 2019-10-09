package com.epam.brest.summer.courses2019.web_app.consumers;


import com.epam.brest.summer.courses2019.Item;
import com.epam.brest.summer.courses2019.ItemService;
import com.epam.brest.summer.courses2019.web_app.ItemController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ItemRestConsumer implements ItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);
    private String url;
    private RestTemplate restTemplate;

    public ItemRestConsumer(String url, RestTemplate restTemplate){
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public Item findItemById(Integer itemId) {
        LOGGER.debug("ItemRestConsumer: findItemById {}", itemId);

        return restTemplate.getForEntity(url+"/"+itemId, Item.class).getBody();
    }

    @Override
    public List<Item> findAllItems() {
        LOGGER.debug("ItemRestConsumer: findAllItems");
        return (List<Item>)restTemplate.getForEntity(url+"/assortment", List.class).getBody();
    }

    @Override
    public Item addItem(Item item) {
        LOGGER.debug("ItemRestConsumer: addItem({})", item);

        return restTemplate.postForEntity(url, item, Item.class).getBody();
    }

    @Override
    public void updateItem(Item item) {
        LOGGER.debug("ItemRestConsumer: updateItem({})", item);

        restTemplate.put(url, item);
    }

    @Override
    public void deleteItem(Integer itemId) {
        LOGGER.debug("ItemRestConsumer: deleteItem({})", itemId);

        restTemplate.delete(url+"/"+itemId);
    }
}
