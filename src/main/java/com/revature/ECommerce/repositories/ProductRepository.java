package com.revature.ECommerce.repositories;

import com.revature.ECommerce.entities.Product;
import com.revature.ECommerce.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class ProductRepository implements HibernateRepository<Product> {
    private Session session;
    public ProductRepository(Session session){
        this.session=session;
    }

    @Override
    public void save(Product product) {
        Transaction tx = session.beginTransaction();
        session.save(product);
        tx.commit();
    }

    @Override
    public List<Product> getAll() {
        String hql = "FROM Product";
        TypedQuery<Product> query = session.createQuery(hql, Product.class);
        return query.getResultList();
    }

    @Override
    public Product getById(Integer id) {
        String hql = "FROM Product WHERE id= :id";
        TypedQuery<Product> query = session.createQuery(hql, Product.class);
        query.setParameter("id", id);
        Product product = query.getSingleResult();
        return product;
    }

    public void deleteProduct(Product product){
        Transaction tx = session.beginTransaction();
        session.remove(product);
        tx.commit();
    }
}
