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
        Users user = new Users();
        user.setUsername(connection.getDisplayName());
        user.setPassword("$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri");
        userRepository.save(user);
        return user.getUsername();
    }
}