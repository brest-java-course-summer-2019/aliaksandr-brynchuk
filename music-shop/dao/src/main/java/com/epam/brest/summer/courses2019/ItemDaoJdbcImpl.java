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

    private static final String SELECT_ALL_AVAILABLE = "select i.item_id, i.group_name, i.item_name, i.item_price "
            + " from items i where item_status = :status order by item_name";
    private static final String SELECT_ITEMS_FROM_ORDER = "select oi.item_id from order_items_list oi where order_id = :orderId";

    private static final String ADD_ITEM = "insert into items (group_name, item_name, item_price)" +
            "values (:groupId, :itemName, :itemPrice)";

    private static final String INSERT_ITEM = "insert into order_items (order_id, item_id) values (:orderId, :itemId)";

    private static final String UPDATE_ITEM = "update items set group_name = :itemGroup, "
            + "item_name = :itemName, item_price = :itemPrice where item_id = :itemId";

    private static final String DELETE_ITEM = "delete from items where item_id = :itemId";

    private static final String DELETE_ITEM_FROM_ORDER = "delete from order_items where order_id = :orderId";

    private static final String FIND_ITEM_BY_ID = "select i.group_name, i.item_name, i.item_price " +
            "from items i where item_id = :itemId";

    private static final String FIND_ITEM_BY_NAME = "select i.item_id, i.group_name, i.item_price " +
            "from items i where item_name = :itemName";

    private static final String FIND_ITEMS_BY_GROUP = "select i.item_id, i.item_name, i.item_price " +
                                                      "from items i where item_group = :itemGroup";


    public ItemDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public boolean succesfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    @Override
    public List<Item> findAllAvailableItems() {
        String status = "available";
        MapSqlParameterSource parameters = new MapSqlParameterSource("item_status", status);
        List<Item> items = namedParameterJdbcTemplate.query(SELECT_ALL_AVAILABLE, parameters, BeanPropertyRowMapper.newInstance(Item.class));
        return items;
    }

    @Override
    public Optional<Item> findItemById(Integer itemId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource("item_id", itemId);

        Item item = namedParameterJdbcTemplate.queryForObject(FIND_ITEM_BY_ID, parameters, BeanPropertyRowMapper.newInstance(Item.class));
        return Optional.ofNullable(item);
    }

    @Override
    public Optional<Item> findItemByName(String itemName) {
        MapSqlParameterSource parameters = new MapSqlParameterSource("item_name", itemName);

        Item item = namedParameterJdbcTemplate.queryForObject(FIND_ITEM_BY_NAME, parameters, BeanPropertyRowMapper.newInstance(Item.class));
        return Optional.ofNullable(item);
    }

    @Override
    public List<Item> findItemsByGroup(String group) {
        MapSqlParameterSource parameters = new MapSqlParameterSource("item_group", group);

        List<Item> itemsFromGroup = itemsList(FIND_ITEMS_BY_GROUP, parameters);
        return itemsFromGroup;
    }

    @Override
    public List<Item> itemsListFromOrder(Integer orderId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource("order_id", orderId);
        List<Item> orderItemsList= itemsList(SELECT_ITEMS_FROM_ORDER, parameters);
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
    public void insertItem(MapSqlParameterSource parameters) {
        namedParameterJdbcTemplate.update(INSERT_ITEM, parameters);
    }

    @Override
    public void deleteItem(Integer itemId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource("item_id", itemId);

        Optional.of(namedParameterJdbcTemplate.update(DELETE_ITEM, parameters))
                .filter(this::succesfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete item from DB"));
    }

    @Override
    public void deleteItemsList(Integer orderId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource("order_id", orderId);
        namedParameterJdbcTemplate.update(DELETE_ITEM_FROM_ORDER, parameters);

    }
}