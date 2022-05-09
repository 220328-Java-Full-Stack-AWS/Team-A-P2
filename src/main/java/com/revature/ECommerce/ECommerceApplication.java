package com.revature.ECommerce;

import com.revature.ECommerce.entities.*;
import com.revature.ECommerce.repo.*;
import com.revature.ECommerce.utilities.HibernateManager;
import com.revature.ECommerce.utilities.TransactionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
		//This initializes our Hibernate manager and passes in the User and Product classes so Hibernate knows what they are

		HibernateManager hibernateManager = new HibernateManager();
		hibernateManager.addAnnotatedClass(User.class);
		hibernateManager.addAnnotatedClass(UserPayment.class);
		hibernateManager.addAnnotatedClass(UserAddress.class);
		hibernateManager.addAnnotatedClass(Product.class);
		hibernateManager.addAnnotatedClass(ProductCategory.class);
		hibernateManager.addAnnotatedClass(ProductInventory.class);
		hibernateManager.addAnnotatedClass(Discount.class);
		hibernateManager.addAnnotatedClass(Admin.class);

		Session session = hibernateManager.initializeDatasource();

		TransactionManager transactionManager = new TransactionManager(session);

		Transaction tx = transactionManager.beginTransaction();

		// user testing
		User leo = new User("leoBarrientos02", "password", "Leonel", "Barrientos", "leobarrientos02@gmail.com", "516-960-8086");
		User tony = new User("ElCucuy", "password", "Tony", "Ferguson", "elCucuy@gmail.com", "917-822-1022");

		// user address testing
		UserAddress leoAddress = new UserAddress("630 Shore Rd.", "Long Beach", "New York", "11561", "United States");
		leoAddress.setUser(leo);

		// user payment testing
		UserPayment tonyPayment = new UserPayment("Credit Card", "Visa", "4263982640269299", "12/2024");
		tonyPayment.setUser(leo);

		session.save(leoAddress);
		session.save(leo);
		session.save(tony);
		session.save(tonyPayment);


		transactionManager.commitTransaction(tx);//unnecessary over-engineering?

		// products test
		transactionManager.beginTransaction();
		Product product1 = new Product("Banana", "Banana is a delicious fruit that is healthy and can be be used for many things", 7.44, "https://th-thumbnailer.cdn-si-edu.com/4Nq8HbTKgX6djk07DqHqRsRuFq0=/1000x750/filters:no_upscale()/https://tf-cmsv2-smithsonianmag-media.s3.amazonaws.com/filer/d5/24/d5243019-e0fc-4b3c-8cdb-48e22f38bff2/istock-183380744.jpg");
		ProductInventory product1Inventory = new ProductInventory(2000);
		product1.setInventory(product1Inventory);

		ProductCategory fruits = new ProductCategory("Fruits", "Healthy food, great for your body and to make amazing healthy recipes.");
		product1.setCategory(fruits);

		Discount summerSale = new Discount("Summer Sale", "We love to provide discounts during the summer for our customers to allow them to have an awesome summer.", 0.25, false);
		session.save(summerSale);

		session.save(fruits);
		session.save(product1Inventory);
		session.save(product1);
		transactionManager.commitTransaction(tx);//unnecessary over-engineering?

		// Admin test
		transactionManager.beginTransaction();
		Admin admin1 = new Admin("LeoTheAdmin", "adminPassword", "Leonel", "Barrientos", "Read-Add-Delete-Edit");
		Admin admin2 = new Admin("StanTheWorker", "adminPassword", "Stan", "Savelev", "Read");
		Admin admin3 = new Admin("TerrellTheManager", "adminPassword", "Terrell", "Crawford", "Read-Add-Delete-Edit");

		session.save(admin1);
		session.save(admin2);
		session.save(admin3);

		UserRepo userRepo = new UserRepo(session);
		AdminRepo adminRepo = new AdminRepo(session);
		ProductCategoryRepo productCategoryRepo = new ProductCategoryRepo(session);
		UserAddressRepo userAddressRepo = new UserAddressRepo(session);
		UserPaymentRepo userPaymentRepo = new UserPaymentRepo(session);
		ProductInventoryRepo productInventoryRepo= new ProductInventoryRepo(session);
		DiscountRepo discountRepo = new DiscountRepo(session);
		ProductRepo productRepo = new ProductRepo(session);

		List<User> users = userRepo.getAll();
		List<Admin> admins = adminRepo.getAll();

		ProductCategory category1 = productCategoryRepo.getById(1);
		List<UserAddress> addressByState = userAddressRepo.getByState("New York");

		List<Product> products = productRepo.getByCategoryId(1);

		List<UserPayment> payments = userPaymentRepo.getByProvider("Visa");

		ProductInventory inventory = productInventoryRepo.getById(1);

		Discount Summer = discountRepo.getByName("Summer Sale");
		transactionManager.commitTransaction(tx);//unnecessary over-engineering?

		for (User user : users){
			System.out.println("Users: " + user.getUsername());
		}

		for(Admin admin : admins){
			System.out.println("Admins: " + admin.getUsername());
		}

		System.out.println(category1.getCategoryName());

		for(UserAddress address : addressByState){
			System.out.println("Addresses from new york: " + address.getAddress() + ", " + address.getCity()+ ", " + address.getState() + ", " + address.getZip());
		}

		for(UserPayment payment : payments){
			System.out.println("Visa Payments: " + payment.getPaymentType() + "," + payment.getProvider() + "," + payment.getAccountNumber());
		}

		System.out.println(inventory.getQuantity());

		System.out.println(Summer.getDiscountName() + ": " + Summer.getDescription());

		for(Product product : products){
			System.out.println(product.getProductName());
		}
	}

}
