package com.revature.ECommerce.repositories.User;

import com.revature.ECommerce.entities.User.User;
import com.revature.ECommerce.repositories.HibernateRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserRepository implements HibernateRepository<User> {

    private Session session;

    public UserRepository(Session session){ this.session = session;}
    @Override
    public void save(User user) {
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
    }

    @Override
    public List<User> getAll() {
        String hql = "From User";
        TypedQuery<User> query = session.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public User getById(Integer id) {
        String hql = "FROM User WHERE id = :id";
        TypedQuery<User> query = session.createQuery(hql);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void delete(User user) {
        Transaction tx = session.beginTransaction();
        session.delete(user);
        tx.commit();
    }

    public User getByUsername(String username){
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);

        Root<User> userTable = query.from(User.class);
        query.select(userTable).where(criteriaBuilder.equal(userTable.get("username"), username));

        return session.createQuery(query).getSingleResult();
    }
}
