package com.epam.brest.summer.courses2019;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ItemDaoJdbcImpl implements ItemDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String SELECT_ALL = "select i.item_id, i.group_id i.firm_id i.item_name i.item_price "
                                            +" from items i order by item_name";

    private static final String ADD_ITEM = "insert into items (group_id, firm_id, item_name, item_price) "
                                          +"values (:group_id, :firm_id, :item_name, :item_price))";

    private static final String UPDATE_ITEM ="";

    private static final String DELETE_ITEM = "delete from items where item_id = :item_id";

    public ItemDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public boolean succesfullyUpdated(int numRowsUpdated){
        return numRowsUpdated>0;
    }

    @Override
    public Item addItem(Item item) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("group_id", item.getItemGroup());
        parameters.addValue("firm_id", item.getItemFirm());
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
                .orElseThrow(()-> new RuntimeException("Failed to update item in DB"));
        

    }

    @Override
    public void deleteItem(Item item) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("item_id", item.getItemId());

        Optional.of(namedParameterJdbcTemplate.update(DELETE_ITEM, parameters))
                .filter(this::succesfullyUpdated)
                .orElseThrow(()-> new RuntimeException("Failed to delete item from DB"));
    }

    @Override
    public List<Item> findAllItems() {
        List<Item> items = namedParameterJdbcTemplate.query(SELECT_ALL, new ItemRowMapper());
        return items;
    }

    public class ItemRowMapper implements RowMapper<Item>{
        @Override
        public Item mapRow(ResultSet resultSet, int i) throws SQLException {
            Item item = new Item();
            item.setItemId(resultSet.getInt("item_id"));
            item.setItemGroup(resultSet.getString("item_group"));
            item.setItemName(resultSet.getString("item_name"));
            item.setItemPrice(resultSet.getBigDecimal("item_price"));
            return item;
        }
    }
}
