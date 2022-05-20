package com.revature.ECommerce;

import com.revature.ECommerce.beans.services.HibernateManager;
import com.revature.ECommerce.entities.Product;
import com.revature.ECommerce.entities.User;
import org.hibernate.Session;
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

		context.start();
		Session session = hibernateManager.getSession();

		// phones
		Product p1 = new Product("Apple iPhone 13", 1232.10, 0, "IPhone 13 is the newest and hottest phone on the market made by Apple. Unlock the power of the apple", "https://i5.walmartimages.com/asr/904ef03b-71de-47b8-b045-c08c860820e8.12bc5a80ee74c14d63eb98d069b0173b.jpeg?odnHeight=612&odnWidth=612&odnBg=FFFFFF","featured","Phones");
		Product p2 = new Product("Apple iPhone 12", 1100.10, 4, "IPhone 12 is the second newest and second hottest phone on the market made by Apple. Unlock the power of the apple", "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/refurb-iphone-12-pro-graphite-2020?wid=2000&hei=1897&fmt=jpeg&qlt=95&.v=1635202842000","available","Phones");
		Product p3 = new Product("Apple iPhone 10", 700.00, 2, "IPhone 10 is the third newest and third hottest phone on the market made by Apple. Unlock the power of the apple", "https://i.ebayimg.com/images/g/TdAAAOSwfiReQjj3/s-l600.jpg","available","Phones");
		Product p4 = new Product("Apple iPhone 8", 500.40, 10, "IPhone 8 is a bang for your buck phone on the market made by Apple. Unlock the power of the apple", "https://i5.walmartimages.com/asr/03a6ac16-b02f-4217-9331-6b162213830e_1.7f8ac5e0f6bcfa29eed9f2258be83847.jpeg?odnHeight=612&odnWidth=612&odnBg=FFFFFF","available","Phones");

		Product p5 = new Product("Apple", .99, 2000, "Unlock the power of the apple", "","available","Phones");

		// users
		User admin = new User("admin","revtech@revature.com","password","admin","admin", "777-777-7777");
		session.save(admin);
		session.save(p1);
  		session.save(p2);
  		session.save(p3);
  		session.save(p4);
  		session.save(p5);
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers", "mode"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "mode"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
