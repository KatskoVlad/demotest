package com.htp.repository.hibernate;

import com.htp.domain.hibernate.HibernateAccount;
import com.htp.repository.GenericDao;

public interface HibernateAccountDao extends GenericDao<HibernateAccount, Long> {
    HibernateAccount findByAccount(String account);

}
