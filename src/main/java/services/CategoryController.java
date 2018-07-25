/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repositories.CategoryRepository;


/**
 *
 * @author lorenzo
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;
    
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
    
    @RequestMapping(/*method = GET,*/ value = "/get-category/{idCategory}")
    public Category getCategory(@PathVariable Long idCategory){
        Category result = categoryRepository.findOne(idCategory);
        System.out.println(result.toString());
        return result;
    }
    
    //FATTO 1) Tutte le categorie
    //2) ricerca di una categoria per id
    //3) lista delle categorie più popolari (collegamento a interest)
    
}
