package com.epam.brest.summer.courses2019;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemDaoJdbcImpl implements ItemDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${item.findAll}")
    private String findAll;

    @Value("${item.selectItemsFromOrder}")
    private String selectItemsFromOrder;

    @Value("${item.addItem}")
    private String addItem;

    @Value("${item.deleteItem}")
    private String deleteItem;

    @Value("${item.updateItem}")
    private String updateItem;

    @Value("${item.insertItem}")
    private String insertItem;

    @Value("${item.findItemById}")
    private String findItemById;

    @Value("${item.deleteItemsFromOrder}")
    private String deleteItemsFromOrder;

    @Value("${item.changeItemStatus}")
    private String changeItemStatus;



    private static final String ITEM_ID = "itemId";
    private static final String ITEM_NAME = "itemName";
    private static final String ITEM_PRICE = "itemPrice";
    private static final String ORDER_ID = "orderId";
    private static final String ITEM_STATUS = "isReserved";


    public ItemDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public boolean succesfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    @Override
    public List<Item> findAllItems() {
        List<Item> items = namedParameterJdbcTemplate.query(findAll, BeanPropertyRowMapper.newInstance(Item.class));
        return items;
    }

    @Override
    public Optional<Item> findItemById(Integer itemId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource(ITEM_ID, itemId);

        Item item = namedParameterJdbcTemplate.queryForObject(findItemById, parameters, BeanPropertyRowMapper.newInstance(Item.class));
        return Optional.ofNullable(item);
    }

    @Override
    public List<Item> itemsListFromOrder(Integer orderId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource(ORDER_ID, orderId);
        List<Item> orderItemsList= itemsList(selectItemsFromOrder, parameters);
        return orderItemsList;
    }


    private List<Item> itemsList(final String sqlRequest, MapSqlParameterSource parameters){
        List<Integer> itemIds = namedParameterJdbcTemplate.queryForList(sqlRequest, parameters, Integer.class);
        List<Item> items = itemIds.stream()
                .map(this::findItemById)
                .map(optional -> optional.orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return items;
    }

    @Override
    public Item addItem(Item item) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(ITEM_NAME, item.getItemName());
        parameters.addValue(ITEM_PRICE, item.getItemPrice());
        parameters.addValue(ITEM_STATUS, item.isReserved());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(addItem, parameters, generatedKeyHolder);
        item.setItemId(generatedKeyHolder.getKey().intValue());
        return item;
    }

    @Override
    public void updateItem(Item item) {
        Optional.of(namedParameterJdbcTemplate.update(updateItem, new BeanPropertySqlParameterSource(item)))
                .filter(this::succesfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update item in DB"));
    }

    @Override
    public void insertItem(MapSqlParameterSource parameters) {
        namedParameterJdbcTemplate.update(insertItem, parameters);
    }

    @Override
    public void deleteItem(Integer itemId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource(ITEM_ID, itemId);

        Optional.of(namedParameterJdbcTemplate.update(deleteItem, parameters))
                .filter(this::succesfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete item from DB"));
    }

    @Override
    public void deleteItemsList(Integer orderId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource(ORDER_ID, orderId);
        namedParameterJdbcTemplate.update(deleteItemsFromOrder, parameters);
    }

    @Override
    public void changeItemStatus(Integer itemId, boolean status){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(ITEM_ID, itemId);
        parameters.addValue(ITEM_STATUS, status);

        namedParameterJdbcTemplate.update(changeItemStatus,parameters);
    }
}
