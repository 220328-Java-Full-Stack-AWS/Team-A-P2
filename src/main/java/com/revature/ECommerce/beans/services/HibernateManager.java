package com.revature.ECommerce.beans.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//This class turns the HibernateManager into a bean, so we can autowire the session for our repos
@Service
public class HibernateManager implements Lifecycle {
    private boolean running = false;

    private final List<Class> annotatedEntities;
    private final Configuration config;
    private Session session;
    private SessionFactory sessionFactory;

    public HibernateManager(){
        config = new Configuration();
        sessionFactory = config.buildSessionFactory();
        annotatedEntities = new ArrayList<>();
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

    @Override
    public void start() {

        for(Class c : annotatedEntities){
            config.addAnnotatedClass(c);
        }
        sessionFactory= config.buildSessionFactory();
        session=sessionFactory.openSession();
        running=true;
    }

    @Override
    public void stop() {
        running=false;
        session.close();
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}

