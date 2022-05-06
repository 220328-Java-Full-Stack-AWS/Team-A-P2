package com.revature.ECommerce.repositories.Product;

import com.revature.ECommerce.entities.Product.Category;
import com.revature.ECommerce.repositories.HibernateRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class CategoryRepository implements HibernateRepository<Category> {

    private Session session;

    public CategoryRepository(Session session){ this.session = session;}

    @Override
    public void save(Category category) {
        Transaction tx = session.beginTransaction();
        session.save(category);
        tx.commit();
    }

    @Override
    public List<Category> getAll() {
        String hql = "From Category";
        TypedQuery<Category> query = session.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public Category getById(Integer id) {
        String hql = "FROM Category WHERE id = :id";
        TypedQuery<Category> query = session.createQuery(hql);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void delete(Category category) {
        Transaction tx = session.beginTransaction();
        session.delete(category);
        tx.commit();
    }
}
