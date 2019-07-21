package com.htp.repository.hibernate.impl;

import com.htp.domain.hibernate.HibernateUser;
import com.htp.repository.hibernate.HibernateUserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("hibernateUserDaoImpl")
public class HibernateUserDaoImpl implements HibernateUserDao {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public List<HibernateUser> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select hu from HibernateUser hu", HibernateUser.class).getResultList();
        }
    }

    @Override
    public HibernateUser findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(HibernateUser.class, id);
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.remove(findById(id));
        }
    }

    @Override
    public HibernateUser save(HibernateUser user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Long hibernateUserId = (Long) session.save(user);
            transaction.commit();
            return session.find(HibernateUser.class, hibernateUserId);
        }
    }

    @Override
    public HibernateUser update(HibernateUser user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(user);
            transaction.commit();
            return session.find(HibernateUser.class, user.getUserId());
        }
    }

    @Override
    public HibernateUser findByNameAndSurname(String name, String surname) {
        try (Session session = sessionFactory.openSession()) {
        Query query = session.createQuery("select hu from HibernateUser hu where hu.name=:name and hu.surname=:surname");
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        List<HibernateUser> hibernateUsers =  query.getResultList();
        return hibernateUsers.get(0);
        }
    }
}
