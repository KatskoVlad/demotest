package com.htp.repository.jdbc;

import com.htp.domain.jdbc.Bank;
import com.htp.repository.GenericDao;

import java.util.List;

public interface CatalogBanksDao extends GenericDao<Bank, Long> {
    Long findByCodeBic(String codeBic);

    List<Bank> search(String query, Integer limit, Integer offset);
}
