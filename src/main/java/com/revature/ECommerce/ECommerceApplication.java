package com.revature.ECommerce;

import com.revature.ECommerce.entities.Product.Category;
import com.revature.ECommerce.entities.Product.Discount;
import com.revature.ECommerce.entities.Product.Inventory;
import com.revature.ECommerce.entities.Product.Product;
import com.revature.ECommerce.entities.User.Address;
import com.revature.ECommerce.entities.User.Payment;
import com.revature.ECommerce.entities.User.User;
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

		hibernateManager.addAnnotatedClass(Product.class);
		hibernateManager.addAnnotatedClass(Category.class);
		hibernateManager.addAnnotatedClass(Inventory.class);
		hibernateManager.addAnnotatedClass(Discount.class);

		Session session= hibernateManager.initializeDatasource();

		TransactionManager transactionManager = new TransactionManager(session);
		Transaction tx = transactionManager.beginTransaction();

		User leo = new User("leoBarrientos", "leobarrientos02@gmail.com", "password", "Leonel", "Barrientos", "516-960-8086");
		Address leoAddress = new Address("630 Shore Rd.", "Long Beach", "New York", "11561", "United States");
		leoAddress.setUser(leo);
		Payment leoPayment = new Payment("Visa", "374245455400126",  "12/2024");
		leoPayment.setUser(leo);

		// Products
		Product product1 = new Product("Playstation 5", "The PlayStation 5 (PS5) is a home video game console developed by Sony Interactive Entertainment.", 797.99, "https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcTA0VKR0M6-jcjWewxw7U2anPcjwE_WGs-KWEYEOfM-6OWvuj_B81m3y8l6blxJ3dLSkRcThFsBAI7DTT31P8Ij8gJPoKT2-w");
		Category product1Category = new Category("PlayStation", "Playstation is a video game console developed by Sony.");
		product1Category.setProduct(product1);
		Inventory product1Inventory = new Inventory(44);
		product1Inventory.setProduct(product1);

		session.save(product1Category);
		session.save(product1Inventory);
		session.save(product1);

		session.save(leoAddress);
		session.save(leoPayment);
		session.save(leo);


		transactionManager.commitTransaction(tx);//unnecessary over-engineering?


	}


}
