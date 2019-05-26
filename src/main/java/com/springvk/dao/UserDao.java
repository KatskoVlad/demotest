package com.springvk.dao;

import com.springvk.entity.User;

import java.util.List;

public interface UserDao extends GenericDao<User, Long> {

    void save (User user);

    User getById(int id);

    List<User> findAll();

    void update(User user);

    void delete(int id);

}
