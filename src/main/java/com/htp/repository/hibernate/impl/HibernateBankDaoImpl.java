package com.htp.repository.hibernate.impl;

import com.htp.domain.hibernate.HibernateBank;
import com.htp.repository.hibernate.HibernateBankDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("hibernateBankDaoImpl")
public class HibernateBankDaoImpl implements HibernateBankDao {


    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;


    @Override
    public List<HibernateBank> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select hb from HibernateBank hb", HibernateBank.class).getResultList();
        }
    }

    @Override
    public HibernateBank findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(HibernateBank.class, id);
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.remove(findById(id));
        }
    }

    @Override
    public HibernateBank save(HibernateBank bank) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Long hibernateBankId = (Long) session.save(bank);
            transaction.commit();
            return session.find(HibernateBank.class, hibernateBankId);
        }
    }

    @Override
    public HibernateBank update(HibernateBank bank) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(bank);
            transaction.commit();
            return session.find(HibernateBank.class, bank.getIdBank());
        }
    }

    @Override
    public List<HibernateBank> findByNameBank(String bankName) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select idBank from HibernateBank hb where hb.nameBank = :nameBank",
                    HibernateBank.class).
                    getResultList();
        }
    }
}
