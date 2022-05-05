package com.revature.ECommerce.utilities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

//This class creates the session for us will add more tomorrow after the full Hibernate lecture
public class HibernateManager {
    private List<Class> annotatedEntities;
    private Session session;
    private SessionFactory sessionFactory;
    public HibernateManager(){
        annotatedEntities=new ArrayList<>();
    }
    public void addAnnotatedClass(Class c){
        annotatedEntities.add(c);
    }
    public Session initializeDatasource(){
        Configuration config = new Configuration();
        for(Class c : annotatedEntities){
            config.addAnnotatedClass(c);
        }
        this.sessionFactory= config.buildSessionFactory();
        this.session=sessionFactory.openSession();
        return this.session;
    }
    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    public Session getSession(){
        return session;
    }
}
