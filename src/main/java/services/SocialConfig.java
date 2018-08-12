/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

/**
 *
 * @author lore
 */
//@Configuration
//@EnableSocial
public class SocialConfig /*implements SocialConfigurer */{
   /* 
    @Autowired
    private FacebookConnectionSignup accountConnectionSignUpService;
    
    @Autowired
    private DataSource dataSource;
    
    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        connectionFactoryConfigurer.addConnectionFactory(new FacebookConnectionFactory(
            environment.getProperty("spring.social.facebook.appId"),
            environment.getProperty("spring.social.facebook.appSecret")));
        
        connectionFactoryConfigurer.addConnectionFactory(new GoogleConnectionFactory(
            environment.getProperty("spring.social.live.appId"),
            environment.getProperty("spring.social.live.appSecret")));
    }
    
    @Override
    public UserIdSource getUserIdSource() {
        return (UserIdSource) new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator cfl) {
     JdbcUsersConnectionRepository repository =
      new JdbcUsersConnectionRepository(dataSource, cfl, Encryptors.noOpText());
     repository.setConnectionSignUp(accountConnectionSignUpService);
     return repository;
    }
*/
}
