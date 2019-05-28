package com.htp.repository.jdbc.impl;

import com.htp.domain.jdbc.User;
import com.htp.repository.jdbc.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    private static final String ID_USER = "id_user";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String DATE_REGISTR = "date_registr";
    private static final String IS_BLOCK = "is_bloked";
    private static final String AGE = "age";
    private static final String ID_ROLE = "id_role";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private User getUserRowMapper(ResultSet resultSet, int i) throws SQLException {
        User user = new User();

        user.setUserId(resultSet.getLong(ID_USER));
        user.setName(resultSet.getString(NAME));
        user.setSurname(resultSet.getString(SURNAME));
        user.setLogin(resultSet.getString(LOGIN));
        user.setPassword(resultSet.getString(PASSWORD));
        user.setEmail(resultSet.getString(EMAIL));
        user.setAge(resultSet.getInt(AGE));
        user.setBlock(resultSet.getBoolean(IS_BLOCK));
        user.setDateRegistr(resultSet.getTimestamp(DATE_REGISTR));
        user.setRoleId(resultSet.getLong(ID_ROLE));

        return user;
    }

    @Override
    public List<User> findAll() {
        final String findAllQuery = "select * from user";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getUserRowMapper);
    }

    @Override
    public User findById(Long id) {
//        final String findById = "select * from user where user_id = ?";
//        return jdbcTemplate.queryForObject(findById, new Object[]{id}, this::getUserRowMapper);
        final String findById = "select * from user where id_user = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_user", id);

        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getUserRowMapper);
    }

    @Override
    public void delete(Long id) {
        final String delete = "delete from user where user_id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id_user", id);

        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public User save(User user) {
        final String createQuery = "INSERT INTO users (name, surname, login, password, age, is_bloked, email, date_registr, id_role) " +
                "VALUES (:name, :surname, :login, :password, :age, :isBloked, :email, :dateRegistr, :roleId);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", user.getName());
        params.addValue("surname", user.getSurname());
        params.addValue("login", user.getLogin());
        params.addValue("password", user.getPassword());
        params.addValue("age", user.getAge());
        params.addValue("email", user.getEmail());
        params.addValue("isBlock", user.getIsBlock());
        params.addValue("dateRegistr", new Date(new Timestamp(System.currentTimeMillis()).getTime()));
        params.addValue("roleId", user.getRoleId());

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder);

        long createdUserId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdUserId);
    }

    @Override
    public User update(User user) {
        final String createQuery = "UPDATE user set name = :name, surname = :surname, " +
                "login = :login, password = :password, phone_number = :phone_number, email = :email, " +
                "creation_date = :creation_date, role_id = :role_id where user_id = :userId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", user.getName());
        params.addValue("surname", user.getSurname());
        params.addValue("login", user.getLogin());
        params.addValue("password", user.getPassword());
        params.addValue("email", user.getEmail());
        params.addValue("age", user.getAge());
        params.addValue("is_bloked", user.getIsBlock());
        params.addValue("date_registr", new Date(new Timestamp(System.currentTimeMillis()).getTime()));
        params.addValue("id_role", user.getRoleId());

        params.addValue("id_user", user.getUserId());

        namedParameterJdbcTemplate.update(createQuery, params);
        return findById(user.getUserId());
    }

    @Override
    public List<User> search(String query, Integer limit, Integer offset) {
        final String searchQuery = "select * from users where lower(name) LIKE lower(:query) or " +
                "lower(surname) LIKE lower(:query) limit :lim offset :off";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("query", "%" + query + "%");
        params.addValue("lim", limit);
        params.addValue("off", offset);

        return namedParameterJdbcTemplate.query(searchQuery, params, this::getUserRowMapper);
    }

    @Override
    public Long findBySurname(String surname) {
        final String findBySurname = "select * from users where lower(surname) = :surname";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("surname", surname.toLowerCase());

        return namedParameterJdbcTemplate.queryForObject(findBySurname, params, this::getUserRowMapper).getUserId();
    }
}
