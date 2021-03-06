package services;

import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import repositories.UserRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EntityScan(basePackages = { "entities" }) 
@EnableJpaRepositories(basePackages = { "repositories" })
@SpringBootApplication
public class BasicApplication {
    
    public static void main(String[] args) {
            SpringApplication.run(BasicApplication.class, args);
    }
}