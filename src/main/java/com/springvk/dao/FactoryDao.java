package com.springvk.dao;

import com.springvk.entity.Factory;

import java.util.List;

public interface FactoryDao extends GenericDao<Factory, Long> {
    List<Long> batchUpdate(List<Factory> users);
//    public List<Factory> search(String query);
}
