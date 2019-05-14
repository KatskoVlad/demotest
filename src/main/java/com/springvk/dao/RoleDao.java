package com.springvk.dao;

import com.springvk.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public interface RoleDao {

    Role getRoleRowMapper(ResultSet resultSet, int i) throws SQLException;
    List<Role> getRolesByUserId(Long userId);
    List<Role> findAll();
    Role findById(Long id);
    void delete(Long id);
    Role save(Role entity);
    Role update(Role entity);

}
