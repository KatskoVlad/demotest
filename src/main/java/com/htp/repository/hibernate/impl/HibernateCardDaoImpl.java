package com.htp.repository.hibernate.impl;

import com.htp.domain.hibernate.HibernateCard;
import com.htp.repository.hibernate.HibernateCardDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("hibernateCardDaoImpl")
public class HibernateCardDaoImpl implements HibernateCardDao {


    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;


    @Override
    public List<HibernateCard> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select hc from HibernateCard hc", HibernateCard.class).getResultList();
            }
        }

    @Override
    public HibernateCard findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(HibernateCard.class, id);
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.remove(findById(id));
        }
    }

    @Override
    public HibernateCard save(HibernateCard card) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Long hibernateCardId = (Long) session.save(card);
            transaction.commit();
            return session.find(HibernateCard.class, hibernateCardId);
        }
    }

    @Override
    public HibernateCard update(HibernateCard card) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(card);
            transaction.commit();
            return session.find(HibernateCard.class, card.getIdCard());
        }
    }

    public HibernateCard findByNumberCard(String numberCard) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(HibernateCard.class, numberCard);
        }
    }
}
