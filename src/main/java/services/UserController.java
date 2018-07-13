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
import repositories.CategoryRepository;
import repositories.InterestRepository;

import entities.Users;
import entities.Category;
import entities.Interest;
import java.util.ArrayList;
import java.util.List;
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
    
    @Autowired
    CategoryRepository categoryRepository;
    
    @Autowired
    InterestRepository interestRepository;
    
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
    
    @RequestMapping(/*method = GET,*/ value = "/findone")
    public Users findOne(Long id){
        Users result=userRepository.findOne(id);
        return result;
    }
    
    @RequestMapping(/*method = GET,*/ value = "/findinterests")
    public Users findInterests(Long id){
        Users result=findOne(id);
        System.out.println(result.getCategories());
        return result;
    }
    
    @RequestMapping(/*method = GET,*/ value = "/addinterest")
    public void addInterest(Long id_user, Long id_category){
        System.out.println(id_user+" "+id_category);
        Users user=userRepository.findOne(id_user);
        Category category=categoryRepository.findOne(id_category);
        Interest interest = new Interest();
        interest.setFkCategory(category);
        interest.setFkUser(user);
        user.addInterest(interest);
        interestRepository.save(interest);
    }
    
    @RequestMapping(/*method = GET,*/ value = "/removeinterest")
    public void removeInterest(Long id_user, Long id_category){
        System.out.println(id_user+" "+id_category);
        Users user=userRepository.findOne(id_user);
        List<Interest> userCategories = user.getCategories();

        Category category=categoryRepository.findOne(id_category);

        for(int i=0; i<userCategories.size(); i++){
            if(userCategories.get(i).getFkCategory()==category){
                interestRepository.delete(userCategories.get(i));
                userCategories.remove(i);
                break;
            }
        }
    }
        
    
    
    
    //2) autenticazione
    //3) inserire una categoria preferita (interessi)
    //4) aggiungere un utente alla lista dei followed
    //5) scaricare la lista di card relativa ai followed legate alle categorie di interesse
    //6) inserire una valutazione ad una card
    //7) scaricare statistiche account
    //*****8) scaricare la lista degli interessi
    //9) modifica informazioni account
    //10) rimuovere una card da quelle preferite
    //11) rimuovere un utente dai followed
    
}
