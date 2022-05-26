package com.revature.ECommerce.beans.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
//        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", CorsConfiguration.ALL);
//        resp.setHeader("Access-Control-Allow-Headers", "Origin, Access-Control-Allow-Origin, Content-Type, " +
//                            "Accept, Authorization, Origin, Accept, X-Requested-With, " +
//                            "Access-Control-Request-Method, Access-Control-Request-Headers, mode");


        if (req.getMethod().equals(HttpMethod.OPTIONS.name())) {
            resp.setStatus(HttpStatus.NO_CONTENT.value());
            System.out.println("OPTIONS RECEIVED");
            return;
        }

        chain.doFilter(req, resp);
    }

}

