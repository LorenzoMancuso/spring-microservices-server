/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.stereotype.Service;
import repositories.UserRepository;

/**
 *
 * @author lore
 */
@Service
class SocialConnectionSignup implements ConnectionSignUp {
 
    @Autowired
    private UserRepository userRepository;

    @Override
    public String execute(Connection<?> connection) {
        System.out.println("execute signUp");
        UserProfile profile=connection.fetchUserProfile();
        
        Users user=userRepository.findOneByEmail(profile.getEmail());
        
        System.out.println("Email: "+profile.getEmail());        
        System.out.println("Username: "+profile.getUsername());
        System.out.println("Name: "+profile.getName());
        System.out.println("First name: "+profile.getFirstName());
        System.out.println("Last name: "+profile.getLastName());
        System.out.println("Username: "+profile.getId());        

        if(user==null){
            user = new Users();
            user.setUsername(profile.getName());
            user.setEmail(profile.getEmail());        
            user.setName(profile.getFirstName());
            user.setSurname(profile.getLastName());
            user.setSubscriptionDate(System.currentTimeMillis() / 1000L);
            user.setPassword("$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri"); 
            user.setCity("");
            user.setCountry("");
            user.setProfession("");

            userRepository.save(user);
        }
        
        System.out.println(user.getUsername());
        return String.valueOf(user.getIdUser());
    }
}