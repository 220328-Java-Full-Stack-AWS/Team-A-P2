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
    private List<Class> annotatedEntities;
    private Configuration config;
    private Session session;
    private SessionFactory sessionFactory;
    public HibernateManager(){
        config = new Configuration();
        annotatedEntities=new ArrayList<>();
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
