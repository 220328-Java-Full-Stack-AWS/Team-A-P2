package com.revature.ECommerce.repositories;

import java.util.List;

public interface HibernateRepository<T> {
    public void save(T t);
    public List<T> getAll();
    public T getById(Integer id);

}
