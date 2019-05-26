package com.springvk.dao;

import java.util.List;

public interface GenericDao<T, K> {

    List<T> findAll();

    T findById(K id);

    void delete(K id);

    void save(T entity);

    void update(T entity);
}
