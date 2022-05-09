package com.revature.ECommerce.repo;

import java.util.List;

public interface HibernateRepo<T> {
    public void save(T t);
    public List<T> getAll();
    public T getById(Integer id);
    public void delete(T t);
}
