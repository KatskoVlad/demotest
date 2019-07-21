package com.htp.repository.hibernate;

import com.htp.domain.hibernate.HibernateCard;
import com.htp.repository.GenericDao;

public interface HibernateCardDao extends GenericDao<HibernateCard, Long> {
    HibernateCard findByNumberCard(String cardNumber);
}
