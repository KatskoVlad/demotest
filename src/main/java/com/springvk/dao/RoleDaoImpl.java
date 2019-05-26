package com.springvk.dao;

import com.springvk.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
public class RoleDaoImpl implements RoleDao {
    public static final String ROLE_ID = "role_id";
    public static final String ROLE_NAME = "role_name";
    public static final String ROLE_USER_ID = "role_user_id";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public RoleDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Role getRoleRowMapper(ResultSet resultSet, int i) throws SQLException {
        Role role = new Role();
        role.setRoleId(resultSet.getInt(ROLE_ID));
        role.setRoleName(resultSet.getString(ROLE_NAME));
        role.setUserId(resultSet.getInt(ROLE_ID));
        return role;

    }


    @Override
    public List<Role> getRolesByUserId(Long userId) {
        final String getRoleByUserId = "select * from roles where role_user_id = ?";
        return jdbcTemplate.query(getRoleByUserId, new Object[]{userId}, this::getRoleRowMapper);
    }

    @Override
    public List<Role> findAll() {
        final String findAllQuery = "select * from role";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getRoleRowMapper);
    }

    @Override
    public Role getById(Long id) {
        return null;
    }

    @Override
    public Role findById(Long id) {
        final String findById = "select * from role where role_id = :roleId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("roleId", id);

        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getRoleRowMapper);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void save(Role entity) {
        final String createQuery = "INSERT INTO role (role_name, role_user_id) " +
                "VALUES (:roleName, :roleUserId);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("roleName", entity.getRoleName());
        params.addValue("roleUserId", entity.getUserId());


        namedParameterJdbcTemplate.update(createQuery, params, keyHolder);

        long createdRoleId = Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public void update(Role entity) {

    }
}
