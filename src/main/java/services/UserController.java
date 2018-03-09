/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//added
import repositories.UserRepository;
import entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    //1) ricerca di un user per id
    //2) autenticazione
    //3) inserire una categoria preferita (interessi)
    //4) aggiungere un utente alla lista dei followed
    //5) scaricare la lista di card relativa ai followed legate alle categorie di interesse
    //6) inserire una valutazione ad una card
    //7) scaricare statistiche account
    //8) scaricare la lista degli interessi
    //9) modifica informazioni account
    //10) rimuovere una card da quelle preferite
    //11) rimuovere un utente dai followed
    
}
