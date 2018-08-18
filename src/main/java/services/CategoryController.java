/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Card;
import entities.Category;
import java.util.Iterator;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repositories.CategoryRepository;


/**
 *
 * @author lorenzo
 */
@RestController
@CrossOrigin
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;
    
    @PersistenceContext
    private EntityManager entityManager;   
    
    @RequestMapping(/*method = GET,*/ value = "/findone")
    public Category findOne(Long id){
        Category result=categoryRepository.findOne(id);
        return result;
    }
    
    //1) Tutte le categorie
    @RequestMapping(/*method = GET,*/ value = "/findall")
    public String findAll(){
        String result = "";

        System.out.println(categoryRepository.findAll());
        
        for(Category cust : categoryRepository.findAll()){
            result += cust + "<br>";
        }
        return result;
    }
    
    //2) ricerca di una categoria per id
    @RequestMapping(/*method = GET,*/ value = "/get-category")
    public Category getCategory(Long idCategory){
        return categoryRepository.findOne(idCategory);
    }
    
    @RequestMapping(/*method = GET,*/ value = "/get-popular-categories")
    public String getPopularCategories() {
        //create an ejbql expression
        String ejbQL = "SELECT DISTINCT cat, COUNT(i) as interestNumber "
                + "FROM Category cat, Interest i "
                + "WHERE cat=i.fkCategory "
                + "GROUP BY cat "
                + "ORDER BY 2";
        //create query
        Query query = entityManager.createQuery(ejbQL);
        //execute the query
        List<Category> result=query.getResultList();
        
        JsonArrayBuilder localComplexCategories = Json.createArrayBuilder();
        JsonObjectBuilder complexCategory;
        //every iteration is a result of the query made by an array of 3 elements: Card, interestNumber, avgRating
        Iterator itr = result.iterator();
        while(itr.hasNext()){
            Object[] obj = (Object[]) itr.next();
            complexCategory=((Category)obj[0]).toJsonObjectBuilder();
            complexCategory.add("interestNumber",Integer.parseInt(String.valueOf(obj[1])));
            localComplexCategories.add(complexCategory.build());
        }
        return localComplexCategories.build().toString();
    }
    
    //***1) Tutte le categorie
    //***2) ricerca di una categoria per id
    //***3) lista delle categorie più popolari (collegamento a interest)
    
}
