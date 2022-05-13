package com.revature.ECommerce.beans.repositories;

import java.util.List;

public interface HibernateRepository<T> {
    public void save(T t);
    public List<T> getAll();
    public T getById(Integer id);
    public T update(T t);

    void start();

    void stop();

    boolean isRunning();
}
