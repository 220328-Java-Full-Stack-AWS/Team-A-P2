//package com.revature.ECommerce.beans.controllers;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Arrays;
//
//@Component
//public class CorsFilter extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        resp.setHeader("Access-Control-Allow-Origin", "*");
////        resp.setHeader("Access-Control-Allow-Methods", "*");
//        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//        resp.setHeader("Access-Control-Allow-Headers", CorsConfiguration.ALL);
////        resp.setHeader("Access-Control-Allow-Headers", "Origin, Access-Control-Allow-Origin, Content-Type, " +
////                            "Accept, Authorization, Origin, Accept, X-Requested-With, " +
////                            "Access-Control-Request-Method, Access-Control-Request-Headers, mode");
//
//
//        if (req.getMethod().equals("OPTIONS")) {
//            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
//            return;
//        }
//
//        chain.doFilter(req, resp);
//        System.out.println("HELLO I AM HERE IN THE CORS FILTER");
//    }
//
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/user").allowedOrigins("http://revtechteamap2-env-1.eba-x9awq5nc.us-west-1.elasticbeanstalk.com");
//            }
//        };
//    }
//
//}
//
