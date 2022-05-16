package com.revature.ECommerce;

import com.revature.ECommerce.beans.repositories.ProductRepository;
import com.revature.ECommerce.entities.*;
import com.revature.ECommerce.beans.services.HibernateManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.sql.Timestamp;
import java.util.List;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication(scanBasePackages = "com.revature.ECommerce.beans")
public class ECommerceApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context =SpringApplication.run(ECommerceApplication.class, args);
		HibernateManager hibernateManager= context.getBean(HibernateManager.class);

		hibernateManager.addEntity(User.class);
		hibernateManager.addEntity(Address.class);
		hibernateManager.addEntity(Payment.class);
		hibernateManager.addEntity(Product.class);
		hibernateManager.addEntity(Order.class);
		hibernateManager.addEntity(Sale.class);

		context.start();

  }
}
