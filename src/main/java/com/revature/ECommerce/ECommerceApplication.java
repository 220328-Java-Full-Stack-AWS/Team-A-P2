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
		//creating all necessary payment, order, address, user, and product objects
		Payment p1 = new Payment(435555334, timestamp, 443);
		Payment p2 = new Payment(4332221, timestamp, 233);
		Address a1= new Address("23 Skidoo Ln.", "New York", "NY", 66666, "USA");
		Address a2 = new Address("69420 High St.", "Los Angeles", "CA", 90210, "USA");
		User Terrell = new User("shady","shady@mail.com", "123", "Terrell", "Crawford", "55569420");
		User Stan = new User("sStan", "stan@mail.com", "123", "Stan", "Savelev", "55588390");
		Product thing1 = new Product("Dress Pants",99.00, 100, "Some nice dress pants", "imgurl", "In Stock", "Clothes");
		//Assigning a sale of 69 Dress pants to Terrell then, creates an order with that sale
		Sale s1 = new Sale(69, timestamp, thing1);
		thing1.setSale(s1);
		Order order= new Order();
		order.setUser(Terrell);
		s1.setOrder(order);
		List<Sale> temp = new ArrayList<>();
		temp.add(s1);
		order.setSaleList(temp);
		List<Order> orders= new ArrayList<>();
		orders.add(order);
		Terrell.setListOfOrders(orders);
		//Sets the addresses and payment methods of Terrell and Stan
		Stan.setAddress(a1);
		a1.setUser(Stan);
		Terrell.setAddress(a2);
		a2.setUser(Terrell);
		Stan.setPayment(p2);
		p1.setUser(Stan);
		Terrell.setPayment(p1);
		p2.setUser(Terrell);
		//persists everything to the DB
		session.save(order);
		session.save(thing1);
		session.save(s1);
		session.save(Stan);
		session.save(Terrell);

		tx.commit();

	}
}
