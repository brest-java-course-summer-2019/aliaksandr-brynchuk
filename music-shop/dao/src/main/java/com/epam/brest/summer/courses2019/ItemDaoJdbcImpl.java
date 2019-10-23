package com.epam.brest.summer.courses2019;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Item DAO implementation, gets data from data base
 */
@Component
public class ItemDaoJdbcImpl implements ItemDao {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemDaoJdbcImpl.class);

    /**
     * NamedParameterJdbcTemplate field
     */
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     *SQL from property file. Find all items
     */
    @Value("${item.findAll}")
    private String findAll;

    /**
     *SQL from property file. Find all items from order
     */
    @Value("${item.selectItemsFromOrder}")
    private String selectItemsFromOrder;

    /**
     *SQL from property file. Add item to db
     */
    @Value("${item.addItem}")
    private String addItem;

    /**
     *SQL from property file. Delete item from db
     */
    @Value("${item.deleteItem}")
    private String deleteItem;

    /**
     *SQL from property file. Update item
     */
    @Value("${item.updateItem}")
    private String updateItem;

    /**
     *SQL from property file. Insert item to order
     */
    @Value("${item.insertItem}")
    private String insertItem;

    /**
     *SQL from property file. Find item by id
     */
    @Value("${item.findItemById}")
    private String findItemById;

    /**
     *SQL from property file. Clear items list
     */
    @Value("${item.deleteItemsFromOrder}")
    private String deleteItemsFromOrder;

    /**
     *SQL from property file. Change item status
     */
    @Value("${item.changeItemStatus}")
    private String changeItemStatus;

    /**
     *Constant fields for sql requests
     */
    private static final String ITEM_ID = "itemId";
    private static final String ITEM_NAME = "itemName";
    private static final String ITEM_PRICE = "itemPrice";
    private static final String ORDER_ID = "orderId";
    private static final String ITEM_STATUS = "isReserved";


    /**
     * Constructor, inject namedParameterJdbcTemplate bean
     *
     * @param namedParameterJdbcTemplate namedParameterJdbcTemplate bean
     */
    @Autowired
    public ItemDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private boolean succesfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    /**
     *Get all items
     *
     * @return Items List
     */
    @Override
    public List<Item> findAllItems() {
        LOGGER.debug("Item DAO: find all items");

        return namedParameterJdbcTemplate.query(findAll, BeanPropertyRowMapper.newInstance(Item.class));
    }

    /**
     *Find item by id
     *
     * @param itemId Item ID
     * @return Optional<>Item</>
     */
    @Override
    public Optional<Item> findItemById(Integer itemId) {
        LOGGER.debug("Item DAO: find item by id({})", itemId);

        MapSqlParameterSource parameters = new MapSqlParameterSource(ITEM_ID, itemId);

        Item item = namedParameterJdbcTemplate.queryForObject(findItemById, parameters, BeanPropertyRowMapper.newInstance(Item.class));
        return Optional.ofNullable(item);
    }

    /**
     * Get items list from order
     *
     * @param orderId Order ID
     * @return Items list
     */
    @Override
    public List<Item> itemsListFromOrder(Integer orderId) {
        LOGGER.debug("Item DAO: find items from order({})", orderId);

        MapSqlParameterSource parameters = new MapSqlParameterSource(ORDER_ID, orderId);
        return namedParameterJdbcTemplate.query(selectItemsFromOrder, parameters, BeanPropertyRowMapper.newInstance(Item.class));
    }

    /**
     * Add item to db
     *
     * @param item Item
     * @return Item
     */
    @Override
    public Item addItem(Item item) {
        LOGGER.debug("Item DAO: add item {}", item);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(ITEM_NAME, item.getItemName());
        parameters.addValue(ITEM_PRICE, item.getItemPrice());
        parameters.addValue(ITEM_STATUS, item.getIsReserved());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(addItem, parameters, generatedKeyHolder);
        item.setItemId(generatedKeyHolder.getKey().intValue());
        return item;
    }

    /**
     * Update item
     *
     * @param item Item
     */
    @Override
    public void updateItem(Item item) {
        LOGGER.debug("Item DAO: update item {}", item);

        Optional.of(namedParameterJdbcTemplate.update(updateItem, new BeanPropertySqlParameterSource(item)))
                .filter(this::succesfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update item in DB"));
    }

    /**
     * Insert item to order
     *
     * @param parameters Parameters to sql request
     */
    @Override
    public void insertItem(MapSqlParameterSource parameters) {
        LOGGER.debug("Item DAO: insert item {}", parameters);

        namedParameterJdbcTemplate.update(insertItem, parameters);
    }

    /**
     * Delete item
     *
     * @param itemId Item Id
     */
    @Override
    public void deleteItem(Integer itemId) {
        LOGGER.debug("Item DAO: delete item {}", itemId);

        MapSqlParameterSource parameters = new MapSqlParameterSource(ITEM_ID, itemId);

        Optional.of(namedParameterJdbcTemplate.update(deleteItem, parameters))
                .filter(this::succesfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete item from DB"));
    }

    /**
     * Clear order items list
     *
     * @param orderId Order id
     */
    @Override
    public void deleteItemsList(Integer orderId) {
        LOGGER.debug("Item DAO: clear items list item {}", orderId);

        MapSqlParameterSource parameters = new MapSqlParameterSource(ORDER_ID, orderId);
        namedParameterJdbcTemplate.update(deleteItemsFromOrder, parameters);
    }

    /**
     * Change item status
     *
     * @param itemId Item ID
     * @param status Item status
     */
    @Override
    public void changeItemStatus(Integer itemId, boolean status) {
        LOGGER.debug("Item DAO: change item status {} - {}", itemId, status);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(ITEM_ID, itemId);
        parameters.addValue(ITEM_STATUS, status);

        namedParameterJdbcTemplate.update(changeItemStatus, parameters);
    }
}
