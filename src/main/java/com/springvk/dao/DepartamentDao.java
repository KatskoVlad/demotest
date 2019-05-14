package com.springvk.dao;

import com.springvk.entity.Departament;

import java.util.List;

public interface DepartamentDao extends GenericDao<Departament, Long> {
    List<Long> batchUpdate(List<Departament> users);
//    public List<Departament> search(String query);
}
