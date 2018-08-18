/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author lore
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;
 
    @Autowired
    private UsersConnectionRepository usersConnectionRepository;
 
    @Autowired
    private SocialConnectionSignup facebookConnectionSignup;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/login*","/signin/**","/signup/**").permitAll()
        .anyRequest().authenticated()
        .and().logout().permitAll()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            //.logoutUrl("/logout")
            .logoutSuccessUrl("/login")
            .deleteCookies("auth_code", "JSESSIONID")
            .invalidateHttpSession(true);
;
        /*.and()
        .formLogin().loginPage("/login").permitAll();*/
    } 
 
    @Bean
    public ProviderSignInController facebookProviderSignInController() {
        ((InMemoryUsersConnectionRepository) usersConnectionRepository)
          .setConnectionSignUp(facebookConnectionSignup);
         
        ProviderSignInController controller=new ProviderSignInController(
          connectionFactoryLocator, 
          usersConnectionRepository, 
          new SocialSignInAdapter());
        controller.setPostSignInUrl("http://192.168.1.20:4200");
        return controller;
    }
    
    /*
    @Bean
    public ProviderSignInController providerSignInController(
                ConnectionFactoryLocator connectionFactoryLocator,
                UsersConnectionRepository usersConnectionRepository) {
        ProviderSignInController controller = new ProviderSignInController(
            connectionFactoryLocator,
            usersConnectionRepository,
            new SocialSignInAdapter());
        controller.setApplicationUrl("http://192.168.1.5:8080");
        return controller;
    }
*/
}
