/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.IOException;
import java.util.Arrays;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.NativeWebRequest;
import repositories.UserRepository;

/**
 *
 * @author lore
 */
@Service
public class SocialSignInAdapter implements SignInAdapter{
    
    UserRepository userRepo;
    @Override
    public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
         
        SecurityContextHolder.getContext().setAuthentication(
          new UsernamePasswordAuthenticationToken(
          connection.getDisplayName(), null, 
          Arrays.asList(new SimpleGrantedAuthority("USER"))));
        //Service code
        System.out.println(localUserId);
        Cookie myCookie =  new Cookie("localUserId", localUserId);  
        request.getNativeResponse(HttpServletResponse.class).addCookie(myCookie);
        
        //added
        
        // -added-
        
        //return "http://192.168.1.5:4200?id="+localUserId+"&connection="+connection;
        return null;
    }
}
