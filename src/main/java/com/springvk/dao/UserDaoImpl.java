package com.springvk.dao;

import com.springvk.entity.User;
import com.springvk.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao{

    @Autowired
    public final JdbcTemplate jdbcTemplate;
    public final String SQL_STRING_QUERY = "SELECT * FROM user";
    public final String SQL_SAVE = "INSERT INTO user (name_user, email, age) VALUES (?, ?, ?)";
    public final String SQL_UPDATE = "UPDATE user SET name_user=?, email=?, age=? WHERE id=?";
    public final String SQL_GET_BY_ID = "SELECT * FROM user WHERE id=?";
    public final String SQL_DELETE = "DELETE FROM user WHERE id=?";

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll() {
        return jdbcTemplate.query(SQL_STRING_QUERY, new UserMapper());
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update(SQL_SAVE, user.getName(), user.getEmail(), user.getAge());
    }

    @Override
    public User getById(int id) {
        return jdbcTemplate.queryForObject(SQL_GET_BY_ID, new UserMapper(), id);
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(SQL_UPDATE, user.getName(), user.getEmail(), user.getAge(), user.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

}
