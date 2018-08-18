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
import entities.Follower;
import entities.Interest;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import repositories.FollowerRepository;
/**
 *
 * @author lorenzo
 */
@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController{
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    CategoryRepository categoryRepository;
    
    @Autowired
    InterestRepository interestRepository;
    
    @Autowired
    FollowerRepository followerRepository;
    
    //***DEFAULT METHODS***
    
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
    //***END DEFAULT METHODS***
    
    //***HELPER METHODS***
    @RequestMapping(/*method = GET,*/ value = "/count-name/{name}")
    public String countUserByName(@PathVariable String name ){
        return userRepository.countByName(name).toString();
    }
    
    //***END HELPER METHODS***

    //***1) GET USER***
    @RequestMapping(/*method = GET,*/ value = "/get-user")
    public String getUser(Long id){
        return userRepository.findOne(id).toString();
    }
    
    //***3) ADD INTEREST***
    @RequestMapping(/*method = GET,*/ value = "/add-interest")
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
    
    //***4) FIND INTERESTS OF USER***
    @RequestMapping(/*method = GET,*/ value = "/find-interests")
    public Users findInterests(Long id){
        Users result=findOne(id);
        System.out.println(result.getCategories());
        return result;
    }
    
    //***5) REMOVE INTEREST OF USER***
    @RequestMapping(/*method = GET,*/ value = "/remove-interest")
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
    
    //***6) ADD FOLLOWED USER***
    @RequestMapping(/*method = GET,*/ value = "/add-followed")
    public String addFollowed(Long id_user, Long id_followed){
        System.out.println(id_user+" "+id_followed);
        
        Users user=userRepository.findOne(id_user);
        Users user_followed=userRepository.findOne(id_followed);
        Follower follower = new Follower();
        follower.setFollowed(user_followed);
        follower.setFollower(user);
        long unixTime = System.currentTimeMillis() / 1000L;
        follower.setFollowDate(unixTime);
        user.addFollowed(follower);
        return followerRepository.save(follower).toString();
    }
    
    //***7) REMOVE FOLLOWED USER***
    @RequestMapping(/*method = GET,*/ value = "/remove-followed")
    public String removeFollowed(Long id_user, Long id_followed){
        System.out.println(id_user+" "+id_followed);
        
        Users user=userRepository.findOne(id_user);
        List<Follower> userFollowed = user.getFollowed();
        Users user_followed=userRepository.findOne(id_followed);
        
        for(int i=0; i<userFollowed.size(); i++){
            if(userFollowed.get(i).getFollowed()==user_followed){
                followerRepository.delete(userFollowed.get(i));
                return userFollowed.remove(i).toString();
            }
        }
        return "Error";
    }
    
    //***9) UPDATE USER INFO***
    @RequestMapping(/*method = GET,*/ value = "/update-user")
    public String updateUser(String jsonObjectStr){
        
        JsonReader jsonReader = Json.createReader(new StringReader(jsonObjectStr));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        
        Users user=userRepository.findOne(object.getJsonNumber("idUser").longValue());
        if(user==null)
            return "Error";
        
        user.setUsername(object.getString("username"));
        user.setName(object.getString("name"));
        user.setSurname(object.getString("surname"));
        user.setBirthDate(object.getJsonNumber("idUser").intValue());
        user.setCountry(object.getString("country"));
        user.setCity(object.getString("city"));
        user.setProfession(object.getString("profession"));
        
        return userRepository.save(user).toString();
    }
    
    /*/ESEMPIO "Enterprise JavaBeans Query Language"
    //https://www.tutorialspoint.com/ejb/ejb_query_language.htm
    public List<Follower> getFollowers(int follower,int followed) {
        //create an ejbql expression
        String ejbQL = "From Follower b where b.follower like ?1 and b.followed like ?2";
        //create query
        Query query = entityManager.createQuery(ejbQL);
        query.setParameter(1,follower);
        query.setParameter(2,followed);
        //substitute parameter.
        query.setParameter(1, "%test%");   
        //execute the query
        return query.getResultList();
   }*/
    
    //***1) ricerca di un user per id
    //2) autenticazione
    //***3) inserire una categoria preferita (interessi)
    //***4) scaricare la lista degli interessi
    //***5) rimuovere un interest
    //***6) aggiungere un utente alla lista dei followed
    //***7) rimuovere un utente dai followed
    //***8) scaricare la lista di followed (? IN GET USER)
    //***9) scaricare la lista di follower (? IN GET USER)
    //11) (BISOGNA DEFINIRE LE STATISTICHE) scaricare statistiche account
    //12) (NON NECESSARIA) modifica informazioni account
    
    
}
