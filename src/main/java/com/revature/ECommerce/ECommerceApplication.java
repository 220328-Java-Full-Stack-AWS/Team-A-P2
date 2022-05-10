package com.revature.ECommerce;

import com.revature.ECommerce.beans.services.ProductService;
import com.revature.ECommerce.beans.services.SalesService;
import com.revature.ECommerce.beans.services.UserService;
import com.revature.ECommerce.entities.Product;
import com.revature.ECommerce.entities.Sales;
import com.revature.ECommerce.entities.User;
import com.revature.ECommerce.beans.services.HibernateManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.revature.ECommerce.beans")
public class ECommerceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ECommerceApplication.class, args);
		//This initializes our Hibernate manager and passes in the entity(Model) classes so Hibernate knows what they are
		HibernateManager hibernateManager= context.getBean(HibernateManager.class);
		hibernateManager.addAnnotatedClass(User.class);
		hibernateManager.addAnnotatedClass(Product.class);
		hibernateManager.addAnnotatedClass(Sales.class);

		context.start();
		Session session = hibernateManager.getSession();
		UserService uServ= context.getBean(UserService.class);
		ProductService pServ= context.getBean(ProductService.class);
		SalesService sServ= context.getBean(SalesService.class);


		Transaction tx = session.beginTransaction();
		User Terrell = new User("shady","shady@mail.com", "123", "Terrell", "Crawford", "55569420");
		Product suitJacket = new Product("Suit Jacket", 200.99, 50, "A fine quality 100% silk suit jacket", "imagelink", "In Stock");
		Product dressPants = new Product("Dress Pants", 30.69, 99, "A comfortable pair of pants that will match any occasion", "imageLink", "In Stock");
		Product ps5 =new Product("Sony Playstation 5", 500.00, 0, "The next generation of Playstation console", "imageLink", "Out of Stock");
		Product ecksBawks =new Product("Microsoft Xbox Series X ", 500.00, 0, "The next generation of Xbox console", "imageLink", "Out of Stock");
		Sales sale = new Sales();
		tx.commit();
		uServ.register(Terrell);
		suitJacket=pServ.saveProduct(suitJacket);
		dressPants=pServ.saveProduct(dressPants);
		ps5=pServ.saveProduct(ps5);
		ecksBawks=pServ.saveProduct(ecksBawks);
		sale =sServ.addToCart(dressPants, sale, 20);
		sale = sServ.addToCart(suitJacket, sale, 20);
		Terrell=sServ.checkout(Terrell, sale);



	}


}
