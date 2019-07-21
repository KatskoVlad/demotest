package com.htp.repository.hibernate.impl;

import com.htp.domain.hibernate.HibernateAccount;
import com.htp.repository.hibernate.HibernateAccountDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("hibernateAccountDaoImpl")
public class HibernateAccountDaoImpl implements HibernateAccountDao {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public List<HibernateAccount> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select ha from HibernateAccount ha", HibernateAccount.class).getResultList();
        }
    }

    @Override
    public HibernateAccount findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(HibernateAccount.class, id);
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.remove(findById(id));
        }
    }

    @Override
    public HibernateAccount save(HibernateAccount account) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Long hibernateAccountId = (Long) session.save(account);
            transaction.commit();
            return session.find(HibernateAccount.class, hibernateAccountId);
        }
    }

    @Override
    public HibernateAccount update(HibernateAccount account) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(account);
            transaction.commit();
            return session.find(HibernateAccount.class, account.getAccountId());
        }
    }
    @Override
    public HibernateAccount findByAccount(String account) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(HibernateAccount.class, account);
        }
    }
}
