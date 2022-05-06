package com.revature.ECommerce;

import com.revature.ECommerce.entities.Address;
import com.revature.ECommerce.entities.Payment;
import com.revature.ECommerce.entities.User;
import com.revature.ECommerce.utilities.HibernateManager;
import com.revature.ECommerce.utilities.TransactionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
		//This initializes our Hibernate manager and passes in the User and Product classes so Hibernate knows what they are

		HibernateManager hibernateManager= new HibernateManager();
		hibernateManager.addAnnotatedClass(User.class);
		hibernateManager.addAnnotatedClass(Payment.class);
		hibernateManager.addAnnotatedClass(Address.class);

		Session session= hibernateManager.initializeDatasource();

		TransactionManager transactionManager = new TransactionManager(session);
		Transaction tx = transactionManager.beginTransaction();

		User leo = new User("leoBarrientos", "leobarrientos02@gmail.com", "password", "Leonel", "Barrientos");
		Address leoAddress = new Address("630 Shore Rd.", "Long Beach", "New York", "11561", "United States", "516-960-8086");
		leoAddress.setUser(leo);

		session.save(leoAddress);
		session.save(leo);

		transactionManager.commitTransaction(tx);//unnecessary over-engineering?


	}


}
