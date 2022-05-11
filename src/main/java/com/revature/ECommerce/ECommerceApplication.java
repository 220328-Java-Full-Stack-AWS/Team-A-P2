package com.revature.ECommerce;

import com.revature.ECommerce.entities.*;
import com.revature.ECommerce.beans.services.HibernateManager;
import org.apache.tomcat.jni.Time;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.Timestamp;

@SpringBootApplication(scanBasePackages = "com.revature.ECommerce.beans")
public class ECommerceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =SpringApplication.run(ECommerceApplication.class, args);
		HibernateManager hibernateManager= context.getBean(HibernateManager.class);
		hibernateManager.addAnnotatedClass(User.class);
		hibernateManager.addAnnotatedClass(Product.class);
		hibernateManager.addAnnotatedClass(Payment.class);
		hibernateManager.addAnnotatedClass(Order.class);
		hibernateManager.addAnnotatedClass(Sale.class);
		hibernateManager.addAnnotatedClass(Address.class);
		context.start();
		Session session = hibernateManager.getSession();
		Timestamp timestamp = new Timestamp(2055, 12, 12, 12, 12, 12,0);
		Transaction tx = session.beginTransaction();
		Payment p1 = new Payment(435555334, timestamp, 443);
		Payment p2 = new Payment(4332221, timestamp, 233);
		Address a1= new Address("23 Skidoo Ln.", "New York", "NY", 66666, "USA");
		Address a2 = new Address("69420 High St.", "Los Angeles", "CA", 90210, "USA");
		//User Terrell = new User("shady","shady@mail.com", "123", "Terrell", "Crawford", "55569420", "69420 High St.", "Los Angeles", "USA", "CA");
		User Terrell = new User("shady","shady@mail.com", "123", "Terrell", "Crawford", "55569420");
		User Stan = new User("sStan", "stan@mail.com", "123", "Stan", "Savelev", "55588390");
		//User Stan = new User("sStan", "stan@mail.com", "123", "Stan", "Savelev", "55588390",  "23 Skidoo Ln.", "New York", "USA", "NY");
		Stan.setAddress(a1);
		a1.setUser(Stan);
		Terrell.setAddress(a2);
		a2.setUser(Terrell);
		Stan.setPayment(p2);
		p1.setUser(Stan);
		Terrell.setPayment(p1);
		p2.setUser(Terrell);
		session.save(Stan);
		session.save(Terrell);

		tx.commit();


	}


}
