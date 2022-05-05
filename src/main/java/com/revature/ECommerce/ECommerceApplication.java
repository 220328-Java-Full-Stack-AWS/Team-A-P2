package com.revature.ECommerce;

import com.revature.ECommerce.entities.Product;
import com.revature.ECommerce.entities.User;
import com.revature.ECommerce.utilities.HibernateManager;
import com.revature.ECommerce.utilities.TransactionManager;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
		//This initializes our Hibernate manager and passes in the User and Product classes so Hibernate knows what they are
		HibernateManager hibernateManager= new HibernateManager();
		hibernateManager.addAnnotatedClass(User.class);
		hibernateManager.addAnnotatedClass(Product.class);

		Session session= hibernateManager.initializeDatasource();



	}


}
