package com.springvk.service;

import com.springvk.entity.Departament;
import com.springvk.dao.DepartamentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository("departamentDao")
public class DepartamentDaoImpl implements DepartamentDao {
    public static final String DEP_ID = "dep_id";
    public static final String DEP_NAME = "dep_name";
    public static final String DEP_CAPACITY = "dep_capacity";
    public static final String FACTORY_ID = "factory_id";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private Departament getDepartamentRowMapper(ResultSet resultSet, int i) throws SQLException {
        Departament departament = new Departament();
        departament.setDepId(resultSet.getLong(DEP_ID));
        departament.setDepName(resultSet.getString(DEP_NAME));
        departament.setDepCapacity(resultSet.getInt(DEP_CAPACITY));
        departament.setFactoryId(resultSet.getLong(FACTORY_ID));
        return departament;
    }

    @Override
    public List<Departament> findAll() {
        final String findAllQuery = "select * from department";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getDepartamentRowMapper);
    }

    @Override
    public Departament findById(Long id) {
        final String findById = "select * from department where dep_id = :depId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("depId", id);

        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getDepartamentRowMapper);
    }

    @Override
    public void delete(Long id) {
        final String delete = "delete from department where dep_id = :dep_id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", id);

        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    public Departament save(Departament entity) {
        final String createQuery = "INSERT INTO department (dep_id, dep_name, dep_capacity, factory_id) " +
                "VALUES (:dep_id, :dep_name, :dep_capacity, :factory_id);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("dep_id", entity.getDepId());
        params.addValue("dep_name", entity.getDepName());
        params.addValue("dep_capacity", entity.getDepCapacity());
        params.addValue("factory_id", entity.getFactoryId());

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder);

        long createdUserId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdUserId);
    }

    @Override
    public Departament update(Departament entity) {
        final String createQuery = "UPDATE department set user_name = :userName, user_surname = :userSurname, " +
                "birth_date = :birthDate, dep_id = :depId where user_id = :userId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("depName", entity.getDepName());
        params.addValue("depCapacity", entity.getDepCapacity());
        params.addValue("factoryId", entity.getFactoryId());
        params.addValue("depId", entity.getDepId());

        namedParameterJdbcTemplate.update(createQuery, params);
        return findById(entity.getDepId());
    }
    @Override
    public List<Long> batchUpdate(List<Departament> departaments) {
        final String createQuery = "UPDATE department set dep_name = :dep_name, dep_capacity = :dep_capacity, " +
                "factory_id = :factory_id where dep_id = :depId";

        List<SqlParameterSource> batch = new ArrayList<>();
        for (Departament departament : departaments) {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("depName", departament.getDepName());
            params.addValue("depCapacity", departament.getDepCapacity());
            params.addValue("factory_id", departament.getFactoryId());
            params.addValue("depId", departament.getDepId());
            batch.add(params);
        }

        namedParameterJdbcTemplate.batchUpdate(createQuery, batch.toArray(new SqlParameterSource[batch.size()]));
        return departaments.stream().map(Departament::getDepId).collect(Collectors.toList());
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
