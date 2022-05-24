package com.revature.ECommerce;

import com.revature.ECommerce.beans.services.HibernateManager;
import com.revature.ECommerce.entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

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

		Transaction tx = session.beginTransaction();

		// phones
		Product p1 = new Product("Apple iPhone 13", 1232.10, 6, "IPhone 13 is the newest and hottest phone on the market made by Apple. Unlock the power of the apple", "https://i5.walmartimages.com/asr/904ef03b-71de-47b8-b045-c08c860820e8.12bc5a80ee74c14d63eb98d069b0173b.jpeg?odnHeight=612&odnWidth=612&odnBg=FFFFFF","featured","Phones");
		Product p2 = new Product("Apple iPhone 12", 1100.10, 4, "IPhone 12 is the second newest and second hottest phone on the market made by Apple. Unlock the power of the apple", "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/refurb-iphone-12-pro-graphite-2020?wid=2000&hei=1897&fmt=jpeg&qlt=95&.v=1635202842000","available","Phones");
		Product p3 = new Product("Apple iPhone 10", 700.00, 2, "IPhone 10 is the third newest and third hottest phone on the market made by Apple. Unlock the power of the apple", "https://i.ebayimg.com/images/g/TdAAAOSwfiReQjj3/s-l600.jpg","available","Phones");
		Product p4 = new Product("Apple iPhone 8", 500.40, 10, "IPhone 8 is a bang for your buck phone on the market made by Apple. Unlock the power of the apple", "https://i5.walmartimages.com/asr/03a6ac16-b02f-4217-9331-6b162213830e_1.7f8ac5e0f6bcfa29eed9f2258be83847.jpeg?odnHeight=612&odnWidth=612&odnBg=FFFFFF","available","Phones");
		Product p5 = new Product("Apple iPhone 6", 299.99, 2, "IPhone 6 is the cheapest iphone on our website made by Apple. Unlock the power of the apple", "https://i5.walmartimages.com/asr/f5b0c710-5bac-4bf5-8703-847ce5cd08ac_1.fab6b943d4776dfcc52a5d87830e94dd.jpeg?odnHeight=612&odnWidth=612&odnBg=FFFFFF","available","Phones");

		// televisions
		Product p6 = new Product("Old TV", 22.99, 1, "This is a old television from 2003, we need to make money so we are still selling it but at a fair price.", "https://www.junkoutinc.com/wp-content/uploads/2020/04/donating-tv-charity.jpg", "discount", "Television");
		session.save(p6);
		Product p7 = new Product("LG 50UK6470 Multisystem 50\" LED TV UHD 4K", 595.95, 10, "50\" UHD 4K UHD Slim Edge Smart LED TV" + "Multi-System, 110 - 220 - 240 Volts, 50/60 Hz IPS 4K Display Active HDR", "https://www.220-electronics.com/media/catalog/product/cache/06e563bb4bf8bb99ff5c3485d61b5ba4/l/a/large02_4_2.jpg", "available", "Television");
		session.save(p7);

		// consoles
		Product p8 = new Product("Oculus Quest", 225.66, 3, "Do you ever feel getting lost in another universe, so you can escape this world? This is the gaming console for you! Virtual reality.", "https://www.rollingstone.com/wp-content/uploads/2021/11/Quest-2-with-packaging-e1637006352194.jpg?w=550", "featured", "Gaming Consoles");
		session.save(p8);
		Product p9 = new Product("Playstation Ps5",765.33, 20,"The playstation is the hottest and latest gaming console released by playstation in 2020. The hardest playstation system to ever buy in history.","https://i5.walmartimages.com/asr/fd596ed4-bf03-4ecb-a3b0-7a9c0067df83.bb8f535c7677cebdd4010741c6476d3a.png?odnHeight=612&odnWidth=612&odnBg=FFFFFF","available","Gaming Consoles");
		session.save(p9);
		Product p10 = new Product("Playstation Ps4",384.99, 8,"The playstation 4 is an iconic video game system made by playstation. You want to play games? Get this!","https://i5.walmartimages.com/asr/c65dea44-aeb6-48d1-93b4-afc77319f291_1.8a33d12192e4719d63b9e6868223a6f3.jpeg?odnHeight=612&odnWidth=612&odnBg=FFFFFF","available","Gaming Consoles");
		session.save(p10);
		Product p11 = new Product("Microsoft Xbox 360",272.99, 12,"One of the best gaming consoles of this decade, made by Microsoft, you want to play games? You want to have fun? Buy this!","https://i5.walmartimages.com/asr/3559c784-6860-4524-a206-1a713c0b2e73.ffb076348e19c4dd9ebb9d590bc635f9.jpeg?odnHeight=612&odnWidth=612&odnBg=FFFFFF","available","Gaming Consoles");
		session.save(p11);
		Product p12 = new Product("Nintendo WII",148.00, 2,"Nintendo wii is one of the best consoles you can get to have a nice time with your friends and family. You want to workout and have fun? Buy this!","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/8008/8008559_sa.jpg","discount","Gaming Consoles");
		session.save(p12);

		// gaming accessories
		Product p13 = new Product("Playstation PS5 controller", 69.00, 21, "You want to play two players on your ps5, you need another controller for which we recommend you to get this black controller.","https://media.direct.playstation.com/is/image/sierialto/dualsense-ps5-controller-black-accessory-front?$Background_Large$","available", "Gaming Accessories");
		session.save(p13);
		Product p14 = new Product("Playstation HD Camera", 59.00, 3, "The playstation camera was made by Playstation for the Ps5, no one really wants the camera so we have it on sale the camera is great for livestreaming.","https://media.direct.playstation.com/is/image/sierialto/hd-camera-ps5-accessory-package","discount", "Gaming Accessories");
		session.save(p14);
		Product p15 = new Product("Turtle Beach - Recon 70 Wired Surround", 34.55, 11, "Built for your next victory, the Turtle Beach® Recon 70 gaming headset officially licensed for Xbox One and Xbox Series X|S. Featuring Turtle Beach’s headset","https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6333/6333660_rd.jpg;maxHeight=640;maxWidth=550","available", "Gaming Accessories");
		session.save(p15);
		Product p16 = new Product("Logitech G502 HERO High Performance Gaming Mouse", 52.99, 4, "G502 HERO features an advanced optical sensor for maximum tracking accuracy, customizable RGB lighting, custom game profiles, and from 200-16,000 DPI.","https://www.quill.com/is/image/Quill/sp30225024_s7?iv=RLYpN3&wid=1080&hei=1080&fit=fit,1","available", "Gaming Accessories");
		session.save(p16);
		// Phone accessories
		Product p17 = new Product("TripGrip Window & Vent Mount for Most Cell Phones", 19.99, 8, " Bracketron's TripGrip is two mounts in one. Mount your phone on your windshield or on an air vent.","https://sm.pcmag.com/t/pcmag_in/gallery/t/the-best-c/the-best-car-phone-mounts-for-2020_7vnk.1200.jpg","discount", "Phone Accessories");
		session.save(p17);

		// users
		User leo = new User("leobarrientos02","leo@gmail.com","password","Leonel","Barrientos", "516-960-8086");
		session.save(leo);
		session.save(p1);
  		session.save(p2);
  		session.save(p3);
  		session.save(p4);
  		session.save(p5);

		User user1 = new User("georgebakhoum", "bakhoumgeorge@gmail.com", "P4ssw0rd!", "George", "Bakhoum", "832-100-1000");
		User user2 = new User("bakgeo", "bakgeo@gmail.com", "gbsw0rd!", "Geo", "Bak", "832-543-2432");
		User user3 = new User("gb", "gb@gmail.com", "simplepassword", "G", "B", "219-999-4543");
		User admin = new User("Admin", "admin@gmail.com", "Password", "admin", "admin", "333-222-1111");

		session.save(admin);
		session.save(user1);
		session.save(user2);
		session.save(user3);

		Address address1 = new Address();
		Payment payment1 = new Payment();
		address1.setUser(leo);
		payment1.setUser(leo);
		session.save(address1);
		session.save(payment1);
		leo.setAddress(address1);
		leo.setPayment(payment1);

		Address address2 = new Address();
		Payment payment2 = new Payment();
		address2.setUser(admin);
		payment2.setUser(admin);
		session.save(address1);
		session.save(payment1);
		admin.setAddress(address2);
		admin.setPayment(payment2);

		Address address3 = new Address();
		Payment payment3 = new Payment();
		address3.setUser(user1);
		payment3.setUser(user1);
		session.save(address3);
		session.save(payment3);
		user1.setAddress(address3);
		user1.setPayment(payment3);

		Address address4 = new Address();
		Payment payment4 = new Payment();
		address4.setUser(user2);
		payment4.setUser(user2);
		session.save(address4);
		session.save(payment4);
		user2.setAddress(address4);
		user2.setPayment(payment4);

		Address address5 = new Address();
		Payment payment5 = new Payment();
		address5.setUser(user3);
		payment5.setUser(user3);
		session.save(address5);
		session.save(payment5);
		user3.setAddress(address5);
		user3.setPayment(payment5);

		tx.commit();
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200","http://localhost:4200/%22"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers", "mode", "user_id"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "mode"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
