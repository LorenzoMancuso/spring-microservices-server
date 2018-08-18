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
import org.springframework.social.connect.ConnectionKey;
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
        
        UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(
          connection.getDisplayName(), null, 
          Arrays.asList(new SimpleGrantedAuthority("USER")));
        
        ConnectionKey key=connection.getKey();
        SecurityContextHolder.getContext().setAuthentication(token);
        System.out.println(request.getSessionId());
        
        //Service code
        System.out.println(localUserId);        
        System.out.println(token);
        String jwt=connection.createData().getAccessToken();
        System.out.println(jwt);
        
        return "http://192.168.1.20:4200/"+localUserId+"/"+request.getSessionId();
    }
}
