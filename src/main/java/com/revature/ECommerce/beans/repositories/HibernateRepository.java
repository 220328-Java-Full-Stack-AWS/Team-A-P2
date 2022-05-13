package com.revature.ECommerce.beans.repositories;

import org.springframework.context.Lifecycle;

import java.util.List;

public interface HibernateRepository<T> extends Lifecycle {
    public T save(T t);
    public List<T> getAll();
    public T getById(Integer id);
    public T update(T t);

    void start();

    void stop();

    boolean isRunning();
}
