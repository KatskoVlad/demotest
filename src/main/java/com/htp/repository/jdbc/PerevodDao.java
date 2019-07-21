package com.htp.repository.jdbc;

import com.htp.domain.jdbc.Perevod;
import com.htp.repository.GenericDao;

import java.util.List;

public interface PerevodDao extends GenericDao<Perevod, Long> {
    List<Perevod> search(String query, Integer limit, Integer offset);
}