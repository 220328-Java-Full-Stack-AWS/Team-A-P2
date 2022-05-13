package com.revature.ECommerce.beans.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HibernateManager implements Lifecycle {
    private boolean running = false;

    private final List<Class> annotatedEntities;
    private final Configuration config;
    private SessionFactory sessionFactory;
    private Session session;

    public HibernateManager(){
        annotatedEntities = new ArrayList<>();
        config = new Configuration();
    }

    public void addAnnotatedClass(Class c){
        annotatedEntities.add(c);
    }

    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public Session getSession(){
        return session;
    }

    public void setSession(Session session){
        this.session = session;
    }

    @Override
    public void start() {
        for(Class c : annotatedEntities){
            config.addAnnotatedClass(c);
        }

        this.sessionFactory = config.buildSessionFactory();
        this.session = sessionFactory.openSession();
        running = true;
    }

    @Override
    public void stop() {
        running = false;
        session.close();
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
