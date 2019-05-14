package com.springvk.mapper;

import com.springvk.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name_user"));
        user.setEmail(resultSet.getString("email"));
        user.setAge(resultSet.getInt("age"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }
}
