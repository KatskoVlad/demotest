package com.htp.repository.hibernate;

import com.htp.domain.hibernate.HibernateBank;
import com.htp.repository.GenericDao;

import java.util.List;

public interface HibernateBankDao extends GenericDao<HibernateBank, Long> {
    List<HibernateBank> findByNameBank(String bankName);
}
