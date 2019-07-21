package com.htp.repository.jdbc.impl;

import com.htp.domain.jdbc.User;
import com.htp.repository.jdbc.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@Qualifier("userDaoImpl")
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
    final String delete = "delete from users where id_user = :idUser";
    final String findAllQuery = "select * from users";
    final String findById = "select * from users where id_user = :idUser";
    final String createQueryUpdate = "UPDATE users set name=:name, surname=:surname, login=:login, password=:password, age=:age, " +
                "is_bloked=isBlock, email=:email, date_registr=:dateRegistr, id_role=:idRole where id_user = :idUser";
    final String createQueryIns = "INSERT INTO users (name, surname, login, password, age, is_bloked, email, date_registr, id_role) " +
                                  "VALUES (:name, :surname, :login, :password, :age, :isBlock, :email, :dateRegistr, :idRole);";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

//    @Autowired
//    private UserRepository userRepository;

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
        user.setIdRole(resultSet.getLong(ID_ROLE));

        return user;
    }

    @Override
    public List<User> findAll() {
        return namedParameterJdbcTemplate.query(findAllQuery, this::getUserRowMapper);
    }

    @Override
    public User findById(Long id) {
//        final String findById = "select * from user where user_id = ?";
//        return jdbcTemplate.queryForObject(findById, new Object[]{id}, this::getUserRowMapper);

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idUser", id);

        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getUserRowMapper);
    }

    @Override
    public void delete(Long id) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idUser", id);

        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public User save(User user) {

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
        params.addValue("idRole", user.getIdRole());

        namedParameterJdbcTemplate.update(createQueryIns, params, keyHolder);

        long createdUserId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdUserId);
    }

    @Override
    public User update(User user) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", user.getName());
        params.addValue("surname", user.getSurname());
        params.addValue("login", user.getLogin());
        params.addValue("password", user.getPassword());
        params.addValue("email", user.getEmail());
        params.addValue("age", user.getAge());
        params.addValue("isBlock", user.getIsBlock());
        params.addValue("dateRegistr", new Date(new Timestamp(System.currentTimeMillis()).getTime()));
        params.addValue("idRole", user.getIdRole());

        params.addValue("idUser", user.getUserId());

        namedParameterJdbcTemplate.update(createQueryUpdate, params);
        return findById(user.getUserId());
    }

    @Override
    public List<User> search(String query, Integer limit, Integer offset) {
        final String searchQuery = "select * from users where lower(name) like lower(:query) or " +
                                   "lower(surname) like lower(:query) order by id_user desc limit :lim offset :off";

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

    @Override
    public User findByLogin(String login) {
        final String findById = "select * from users where login = :login";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("login", login);

        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getUserRowMapper);
    }

    @Override
    public void add(User user) {
        save(user);
    }
}
