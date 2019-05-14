package com.springvk.dao;

import com.springvk.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    void save (User user);

    User getById(int id);

    List<User> findAll();

    void update(User user);

    void delete(int id);

}
