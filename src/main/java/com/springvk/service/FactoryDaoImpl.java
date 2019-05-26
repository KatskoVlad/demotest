package com.springvk.service;

import com.springvk.dao.FactoryDao;
import com.springvk.entity.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class FactoryDaoImpl implements FactoryDao {
    public static final String FACTORY_ID = "factory_id";
    public static final String FACTORY_NAME = "factory_name";
    public static final String FACTORY_OPEN_YEAR = "factoryOpenYear";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private Factory getFactoryRowMapper(ResultSet resultSet, int i) throws SQLException {
        Factory Factory = new Factory();
        Factory.setFactoryId(resultSet.getLong(FACTORY_ID));
        Factory.setFactoryName(resultSet.getString(FACTORY_NAME));
        Factory.setFactoryOpenYear(resultSet.getTimestamp(FACTORY_OPEN_YEAR));
        Factory.setFactoryId(resultSet.getLong(FACTORY_ID));
        return Factory;
    }

    @Override
    public List<Factory> findAll() {
        final String findAllQuery = "select * from factory";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getFactoryRowMapper);
    }

    @Override
    public Factory findById(Long id) {
        final String findById = "select * from factory where factory_id = :factoryId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("factoryId", id);

        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getFactoryRowMapper);
    }

    @Override
    public void delete(Long id) {
        final String delete = "delete from factory where factory_id = :factory_id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("factoryId", id);

        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    public void save(Factory entity) {
        final String createQuery = "INSERT INTO factory (factory_id, factory_name, factory_open_year) " +
                "VALUES (:factory_id, :factory_name, :factory_open_year);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("factory_id", entity.getFactoryId());
        params.addValue("factory_name", entity.getFactoryName());
        params.addValue("factory_open_year", entity.getFactoryOpenYear());

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder);

        long createdFactoryId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        //return findById(createdFactoryId);
    }

    @Override
    public void update(Factory entity) {
        final String createQuery = "UPDATE factory set factory_name = :factory_name, factory_open_year =:factory_open_year, " +
                " where factory_id = :factory_id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("factory_name", entity.getFactoryName());
        params.addValue("factory_open_year", entity.getFactoryOpenYear());
        params.addValue("factory_id", entity.getFactoryId());

        namedParameterJdbcTemplate.update(createQuery, params);
        //return findById(entity.getFactoryId());
    }
    @Override
    public List<Long> batchUpdate(List<Factory> factorys) {
        final String createQuery = "UPDATE factory set factory_name = :factory_name, factory_open_year = :factory_open_year, " +
                "where factory_id = :factoryId";

        List<SqlParameterSource> batch = new ArrayList<>();
        for (Factory Factory : factorys) {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("factoryName", Factory.getFactoryName());
            params.addValue("factoryId", Factory.getFactoryId());
            params.addValue("factoryOpenYear", Factory.getFactoryOpenYear());
            batch.add(params);
        }

        namedParameterJdbcTemplate.batchUpdate(createQuery, batch.toArray(new SqlParameterSource[batch.size()]));
        return factorys.stream().map(Factory::getFactoryId).collect(Collectors.toList());
    }
    public int[] batchUpdate(String sql, Map<String, ?>[] batchValues) {
        return namedParameterJdbcTemplate.batchUpdate(sql, batchValues);
    }

    public int[] batchUpdate(String sql, SqlParameterSource[] batchArgs) {
        return namedParameterJdbcTemplate.batchUpdate(sql, batchArgs);
    }

    public <T> int[] batchUpdate(String sql, List<T> entityList) {
        int listSize = entityList.size();
        SqlParameterSource[] sqlParameterSources = new SqlParameterSource[listSize];
        for (int i = 0; i < listSize; i++) {
            sqlParameterSources[i] = new BeanPropertySqlParameterSource(entityList.get(i));
        }
        return batchUpdate(sql, sqlParameterSources);
    }
}
