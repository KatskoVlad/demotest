package com.htp.repository.hibernate.impl;

import com.htp.domain.hibernate.HibernatePerevod;
import com.htp.repository.hibernate.HibernatePerevodDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("hibernatePerevodDaoImpl")
public class HibernatePerevodDaoImpl implements HibernatePerevodDao {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public List<HibernatePerevod> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select hp from HibernatePerevod hp", HibernatePerevod.class).getResultList();
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.remove(findById(id));
        }
    }

    @Override
    public HibernatePerevod findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(HibernatePerevod.class, id);
        }
    }

    @Override
    public HibernatePerevod save(HibernatePerevod perevod) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Long hibernateUserId = (Long) session.save(perevod);
            transaction.commit();
            return session.find(HibernatePerevod.class, hibernateUserId);
        }
    }

    @Override
    public HibernatePerevod update(HibernatePerevod perevod) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(perevod);
            transaction.commit();
            return session.find(HibernatePerevod.class, perevod.getPerevodId());
        }
    }
}
