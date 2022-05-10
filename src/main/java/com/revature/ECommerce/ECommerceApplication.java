package com.revature.ECommerce;

import com.revature.ECommerce.entities.Product;
import com.revature.ECommerce.entities.User;
import com.revature.ECommerce.beans.services.HibernateManager;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.revature.ECommerce.beans")
public class ECommerceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =SpringApplication.run(ECommerceApplication.class, args);
		HibernateManager hibernateManager= context.getBean(HibernateManager.class);
		hibernateManager.addAnnotatedClass(User.class);
		hibernateManager.addAnnotatedClass(Product.class);

		context.start();
		Session session = hibernateManager.getSession();




	}


}
