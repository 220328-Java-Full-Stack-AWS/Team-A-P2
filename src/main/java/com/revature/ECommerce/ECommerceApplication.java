package com.revature.ECommerce;

import com.revature.ECommerce.entities.Product;
import com.revature.ECommerce.entities.Sales;
import com.revature.ECommerce.entities.User;
import com.revature.ECommerce.repositories.UserRepository;
import com.revature.ECommerce.utilities.HibernateManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
		//This initializes our Hibernate manager and passes in the entity(Model) classes so Hibernate knows what they are
		HibernateManager hibernateManager= new HibernateManager();
		hibernateManager.addAnnotatedClass(User.class);
		hibernateManager.addAnnotatedClass(Product.class);
		hibernateManager.addAnnotatedClass(Sales.class);

		Session session= hibernateManager.initializeDatasource();

		Transaction tx = session.beginTransaction();
		User Terrell = new User("shady","shady@mail.com", "123", "Terrell", "Crawford", "55569420");
		Product suitJacket = new Product("Suit Jacket", 200.99, 50, "A fine quality 100% silk suit jacket", "imagelink", "In Stock");
		Product dressPants = new Product("Dress Pants", 30.69, 99, "A comfortable pair of pants that will match any occasion", "imageLink", "In Stock");
		Product ps5 =new Product("Sony Playstation 5", 500.00, 0, "The next generation of Playstation console", "imageLink", "Out of Stock");
		Product ecksBawks =new Product("Microsoft Xbox Series X ", 500.00, 0, "The next generation of Playstation console", "imageLink", "Out of Stock");
		session.save(Terrell);
		session.save(suitJacket);
		session.save(dressPants);
		session.save(ps5);
		session.save(ecksBawks);
		UserRepository uRepo= new UserRepository(session);
		List<User> userList= uRepo.getAll();
		System.out.println(userList);
		tx.commit();



	}


}
