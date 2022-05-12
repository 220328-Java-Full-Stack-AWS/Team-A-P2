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
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication(scanBasePackages = "com.revature.ECommerce.beans")
public class ECommerceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ECommerceApplication.class, args);
		HibernateManager hibernateManager = context.getBean(HibernateManager.class);
		hibernateManager.addAnnotatedClass(User.class);
		hibernateManager.addAnnotatedClass(Product.class);
		hibernateManager.addAnnotatedClass(Payment.class);
		hibernateManager.addAnnotatedClass(Order.class);
		hibernateManager.addAnnotatedClass(Sale.class);
		hibernateManager.addAnnotatedClass(Address.class);
		context.start();
		Session session = hibernateManager.getSession();

		User Jack = new User("Jak","fake@gmail.com","125","Jack","Jackson","8004206969");
		session.save(Jack);
	}
}
