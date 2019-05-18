package com.springvk.mapper;

import com.springvk.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id_user"));
        user.setName(resultSet.getString("name"));
        user.setSurName(resultSet.getString("surname"));
        user.setEmail(resultSet.getString("email"));
        user.setAge(resultSet.getInt("age"));
        user.setIdRole(resultSet.getLong("id_role"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setBlocked(resultSet.getBoolean("is_bloked"));
        return user;
    }
}
