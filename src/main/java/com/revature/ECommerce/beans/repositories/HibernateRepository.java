package com.revature.ECommerce.beans.repositories;

import org.hibernate.Session;
import org.springframework.context.Lifecycle;
import java.io.Serializable;
import java.util.List;

public interface HibernateRepository<T> extends Lifecycle {
    public T save(T t);
    public T update(T t);
    public List<T> getAll();
    public T getById(Integer id);

}
