package com.revature.ECommerce.repo;

import com.revature.ECommerce.entities.Discount;
import com.revature.ECommerce.entities.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class ProductRepo implements HibernateRepo<Product> {

    private Session session;

    public ProductRepo(Session session){ this.session = session;}

    @Override
    public void save(Product product) {
        Transaction tx = session.beginTransaction();
        session.save(product);
        tx.commit();
    }

    @Override
    public List<Product> getAll() {
        String hql = "From Product";
        TypedQuery<Product> query = session.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public Product getById(Integer id) {
        String hql = "FROM Product WHERE id = :id";
        TypedQuery<Product> query = session.createQuery(hql);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void delete(Product product) {
        Transaction tx = session.beginTransaction();
        session.delete(product);
        tx.commit();
    }

    public List<Product> getByCategoryId(Integer category_id) {
        String hql = "FROM Product WHERE category_id = :category_id";
        TypedQuery<Product> query = session.createQuery(hql);
        query.setParameter("category_id", category_id);
        return query.getResultList();
    }
}
