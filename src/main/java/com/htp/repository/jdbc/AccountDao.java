package com.htp.repository.jdbc;

import com.htp.domain.jdbc.Account;
import com.htp.repository.GenericDao;

import java.util.List;

public interface AccountDao extends GenericDao<Account, Long> {
    Long findByAccount(String accountName);

    List<Account> search(String query, Integer limit, Integer offset);
}
