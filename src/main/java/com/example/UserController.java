/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

//import java.util.List;

/*import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;*/
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;*/

//added
import repositories.UserRepository;
import entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author lorenzo
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;
    
    @RequestMapping(/*method = GET,*/ value = "/findall")
    public String findAll(){
        String result = "";

        System.out.println(userRepository.findAll());
        
        for(Users cust : userRepository.findAll()){
            result += cust + "<br>";
        }
        return result;
    }
    
    @RequestMapping(/*method = GET,*/ value = "/count")
    public String count(){
        String result = "COUNT: "+userRepository.count();
        return result;
    }
    
    /*@RequestMapping(method = GET)
    public List<Object> list() {
        return null;
    }
    
    @RequestMapping(value = "/{id}", method = GET)
    public Object get(@PathVariable String id) {
        return null;
    }
    
    @RequestMapping(value = "/{id}", method = PUT)
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
        return null;
    }
    
    @RequestMapping(value = "/{id}", method = POST)
    public ResponseEntity<?> post(@PathVariable String id, @RequestBody Object input) {
        return null;
    }
    
    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<Object> delete(@PathVariable String id) {
        return null;
    }*/
    
}
