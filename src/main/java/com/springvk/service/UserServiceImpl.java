package com.springvk.service;

import com.springvk.dao.UserDao;
import com.springvk.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/** Business logic is here */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserDao userDao;

    public List<User> findAll() {
        return userDao.findAll();
    }

    public void save(User user) {
        userDao.save(user);
    }

    public User getById(int id) {
        return userDao.getById(id);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void delete(int id) {
        userDao.delete(id);
    }

}
