package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import repositories.UserRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = { "entities" }) 
@EnableJpaRepositories(basePackages = { "repositories" })
@SpringBootApplication
public class BasicApplication {
    
    public static void main(String[] args) {
            SpringApplication.run(BasicApplication.class, args);
    }
}
