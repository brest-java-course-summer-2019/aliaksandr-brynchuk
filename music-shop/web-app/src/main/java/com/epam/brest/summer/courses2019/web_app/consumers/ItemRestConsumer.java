package com.epam.brest.summer.courses2019.web_app.consumers;


import com.epam.brest.summer.courses2019.Item;
import com.epam.brest.summer.courses2019.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Item rest consumer
 */
public class ItemRestConsumer implements ItemService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemRestConsumer.class);

    /**
     * Request URL
     */
    private String url;

    /**
     * Rest template field
     */
    private RestTemplate restTemplate;

    /**
     * Constructor
     *
     * @param url Request URL
     * @param restTemplate Rest template
     */
    public ItemRestConsumer(String url, RestTemplate restTemplate){
        this.url = url;
        this.restTemplate = restTemplate;
    }

    /**
     * Find item by ID
     *
     * @param itemId Item Id
     * @return Item
     */
    @Override
    public Item findItemById(Integer itemId) {
        LOGGER.debug("ItemRestConsumer: findItemById {}", itemId);

        return restTemplate.getForEntity(url+"/"+itemId, Item.class).getBody();
    }

    /**
     * Find all items
     *
     * @return response entity body (Items list)
     */
    @Override
    public List<Item> findAllItems() {
        LOGGER.debug("ItemRestConsumer: findAllItems {}", url);

        return restTemplate.exchange(url + "/assortment",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Item>>(){}).getBody();
    }


    /**
     * Add item
     *
     * @param item response entity body(Item)
     */
    @Override
    public void addItem(Item item) {
        LOGGER.debug("ItemRestConsumer: addItem({})", item);

        restTemplate.postForEntity(url, item, Item.class).getBody();
    }

    /**
     * Update item
     *
     * @param item Item
     */
    @Override
    public void updateItem(Item item) {
        LOGGER.debug("ItemRestConsumer: updateItem({})", item);

        restTemplate.put(url, item);
    }

    /**
     * Delete item
     *
     * @param itemId Item Id
     */
    @Override
    public void deleteItem(Integer itemId) {
        LOGGER.debug("ItemRestConsumer: deleteItem({})", itemId);

        restTemplate.delete(url+"/"+itemId+"/delete");
    }
}
