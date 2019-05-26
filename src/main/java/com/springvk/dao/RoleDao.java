package com.springvk.dao;

import com.springvk.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public interface RoleDao extends GenericDao<Role, Long > {
    Role getRoleRowMapper(ResultSet resultSet, int i) throws SQLException;
    List<Role> getRolesByUserId(Long userId);
    List<Role> findAll();
    Role getById(Long id);
    void delete(Long id);
    void save(Role entity);
    void update(Role entity);
}
