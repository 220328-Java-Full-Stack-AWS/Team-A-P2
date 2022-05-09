package com.revature.ECommerce.repo;

import com.revature.ECommerce.entities.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class AdminRepo implements HibernateRepo<Admin> {

    private Session session;

    public AdminRepo(Session session){this.session = session;}

    @Override
    public void save(Admin admin) {
        Transaction tx = session.beginTransaction();
        session.save(admin);
        tx.commit();
    }

    @Override
    public List<Admin> getAll() {
        String hql = "From Admin";
        TypedQuery<Admin> query = session.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public Admin getById(Integer id) {
        String hql = "FROM Admin WHERE id = :id";
        TypedQuery<Admin> query = session.createQuery(hql);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void delete(Admin admin) {
        Transaction tx = session.beginTransaction();
        session.delete(admin);
        tx.commit();
    }

    public Admin getByUsername(String username){
        String hql = "FROM Admin WHERE username = :username";
        TypedQuery<Admin> query = session.createQuery(hql);
        query.setParameter("username", username);
        return query.getSingleResult();
    }
}
