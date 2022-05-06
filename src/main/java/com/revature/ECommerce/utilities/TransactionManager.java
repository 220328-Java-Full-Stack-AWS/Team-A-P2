package com.revature.ECommerce.utilities;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TransactionManager {

    private Session session;

    public TransactionManager(Session session){
        this.session = session;
    }

    public Transaction beginTransaction(){
        return session.beginTransaction();
    }

    public void commitTransaction(Transaction tx){
        tx.commit();
    }
}
