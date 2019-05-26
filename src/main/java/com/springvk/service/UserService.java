package com.springvk.service;

import com.springvk.dao.GenericDao;
import com.springvk.entity.User;

import java.util.List;

public interface UserService extends GenericDao<User, Long> {
    List<User> findAll();

    void save (User user);

    User getById(int id);

    void update(User user);

    void delete(int id);
}
