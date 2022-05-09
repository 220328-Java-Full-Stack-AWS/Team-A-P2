package com.revature.ECommerce.repo;

import com.revature.ECommerce.entities.ProductCategory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class ProductCategoryRepo implements HibernateRepo<ProductCategory> {

    private Session session;

    public ProductCategoryRepo(Session session){ this.session = session;}

    @Override
    public void save(ProductCategory category) {
        Transaction tx = session.beginTransaction();
        session.save(category);
        tx.commit();
    }

    @Override
    public List<ProductCategory> getAll() {
        String hql = "From ProductCategory";
        TypedQuery<ProductCategory> query = session.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public ProductCategory getById(Integer id) {
        String hql = "FROM ProductCategory WHERE id = :id";
        TypedQuery<ProductCategory> query = session.createQuery(hql);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void delete(ProductCategory category) {
        Transaction tx = session.beginTransaction();
        session.delete(category);
        tx.commit();
    }

    public ProductCategory getByCategoryName(String category_name) {
        String hql = "FROM ProductCategory WHERE category_name = :category_name";
        TypedQuery<ProductCategory> query = session.createQuery(hql);
        query.setParameter("category_name", category_name);
        return query.getSingleResult();
    }
}
