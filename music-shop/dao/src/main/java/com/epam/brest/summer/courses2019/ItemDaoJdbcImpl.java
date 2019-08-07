package com.epam.brest.summer.courses2019;

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

    private static final String SELECT_ALL = "select i.item_id, i.group_name, i.item_name, i.item_price "
            + " from items i order by item_name";
    private static final String SELECT_ITEMS_FROM_ORDER = "select oi.item_id from order_items_list oi where order_id = :order_id";

    private static final String ADD_ITEM = "insert into items (group_name, item_name, item_price)" +
            "values (:group_id, :firm_id, :item_name, :item_price)";

    private static final String UPDATE_ITEM = "update items set group_name = :item_name, "
            + "item_name = :item_name, item_price = :item_price where item_id = :item_id";

    private static final String DELETE_ITEM = "delete from items where item_id = :item_id";

    private static final String FIND_ITEM_BY_ID = "select i.group_name, i.item_name, i.item_price " +
            "from items i where item_id = :item_id";

    public ItemDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public boolean succesfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    @Override
    public Optional<Item> findItemById(Integer itemId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource("item_id", itemId);

        Item item = namedParameterJdbcTemplate.queryForObject(FIND_ITEM_BY_ID, parameters, BeanPropertyRowMapper.newInstance(Item.class));
        return Optional.ofNullable(item);
    }

    @Override
    public List<Item> findAllItems() {
        List<Item> items = namedParameterJdbcTemplate.query(SELECT_ALL, BeanPropertyRowMapper.newInstance(Item.class));
        return items;
    }

    public List<Item> itemsListFromOrder(Integer orderId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource("order_id", orderId);
        List<Integer> itemIds = namedParameterJdbcTemplate.queryForList(SELECT_ITEMS_FROM_ORDER, parameters, Integer.class);
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
        parameters.addValue("group_id", item.getItemGroup());
        parameters.addValue("item_name", item.getItemName());
        parameters.addValue("item_price", item.getItemPrice());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(ADD_ITEM, parameters, generatedKeyHolder);
        item.setItemId(generatedKeyHolder.getKey().intValue());
        return item;
    }

    @Override
    public void updateItem(Item item) {
        Optional.of(namedParameterJdbcTemplate.update(UPDATE_ITEM, new BeanPropertySqlParameterSource(item)))
                .filter(this::succesfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update item in DB"));
    }

    @Override
    public void deleteItem(Integer itemId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource("item_id", itemId);

        Optional.of(namedParameterJdbcTemplate.update(DELETE_ITEM, parameters))
                .filter(this::succesfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete item from DB"));
    }
}
