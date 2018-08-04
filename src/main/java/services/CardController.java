/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Card;
import entities.Follower;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import repositories.CardRepository;

/**
 *
 * @author lorenzo
 */
@RestController
@RequestMapping("/cards")
public class CardController {
    
    @Autowired
    CardRepository cardRepository;
    
    @PersistenceContext
    private EntityManager entityManager;   
    
    //***GET CARD***
    @RequestMapping(/*method = GET,*/ value = "/get-card")
    public String getUser(Long id){
        return cardRepository.findOne(id).toString();
    }
    
    //***6) GET ALL CARD FROM ONE CATEGORY***
    //ESEMPIO "Enterprise JavaBeans Query Language"
    //https://www.tutorialspoint.com/ejb/ejb_query_language.htm
    @RequestMapping(/*method = GET,*/ value = "/get-category-cards")
    public String getCategoryCards(Long idCategory) {
        //create an ejbql expression SELECT DISTINCT d FROM Department d, Student e WHERE d = e.department"
        String ejbQL = "SELECT DISTINCT c, COUNT(r) as interestNumber, AVG(r.value) as avgRating "
                + "FROM Card c, CardCategory ct, Rating r "
                + "WHERE ct.fkCategory.idCategory=?1 AND ct.fkCategory MEMBER OF c.categories AND r.fkCard=c "
                + "GROUP BY c "
                + "ORDER BY 2,3";
        //create query
        Query query = entityManager.createQuery(ejbQL);
        query.setParameter(1,idCategory);
        //execute the query
        List<Object> result=query.getResultList();
        
        JsonArrayBuilder localComplexCards = Json.createArrayBuilder();
        JsonObjectBuilder complexCard;
        //every iteration is a result of the query made by an array of 3 elements: Card, interestNumber, avgRating
        Iterator itr = result.iterator();
        while(itr.hasNext()){
            Object[] obj = (Object[]) itr.next();
            System.out.println(obj.length);
            complexCard=((Card)obj[0]).toJsonObjectBuilder();
            complexCard.add("interestNumber",Integer.parseInt(String.valueOf(obj[1])));
            complexCard.add("avgRating",Float.parseFloat(String.valueOf(obj[2])));
            localComplexCards.add(complexCard.build());
        }

        return localComplexCards.build().toString();
    }
    
    //***7) GET ALL followed CARD in interest***
    @RequestMapping(/*method = GET,*/ value = "/get-followed-cards")
    public String getFollowedCards(Long idUser) {
        //create an ejbql expression SELECT DISTINCT d FROM Department d, Student e WHERE d = e.department"
        String ejbQL = "SELECT DISTINCT c, COUNT(r) as interestNumber, AVG(r.value) as avgRating "
                + "FROM Card c, Rating r, Users u "
                + "WHERE u.idUser=?1 AND c.fkUser IN (SELECT f.followed FROM Follower f WHERE f.follower=u) AND "
                + "(EXISTS (SELECT i.fkCategory FROM Interest i WHERE i.fkUser=u AND i.fkCategory MEMBER OF c.categories)) AND r.fkCard=c "
                + "GROUP BY c "
                + "ORDER BY 2,3";
        //create query
        Query query = entityManager.createQuery(ejbQL);
        query.setParameter(1,idUser);
        //execute the query
        List<Object> result=query.getResultList();
        
        JsonArrayBuilder localComplexCards = Json.createArrayBuilder();
        JsonObjectBuilder complexCard;
        
        //every iteration is a result of the query made by an array of 3 elements: Card, interestNumber, avgRating
        Iterator itr = result.iterator();
        while(itr.hasNext()){
            Object[] obj = (Object[]) itr.next();
            System.out.println(obj.length);
            complexCard=((Card)obj[0]).toJsonObjectBuilder();
            complexCard.add("interestNumber",Integer.parseInt(String.valueOf(obj[1])));
            complexCard.add("avgRating",Float.parseFloat(String.valueOf(obj[2])));
            localComplexCards.add(complexCard.build());
        }
        
        return localComplexCards.build().toString();
    }
    
    //***7) GET ALL followed CARD in interest***
    @RequestMapping(/*method = GET,*/ value = "/get-popular-cards")
    public String getPopularCards(Long idUser) {
        //create an ejbql expression SELECT DISTINCT d FROM Department d, Student e WHERE d = e.department"
        String ejbQL = "SELECT DISTINCT c, COUNT(r) as interestNumber, AVG(r.value) as avgRating "
                + "FROM Card c, Rating r, Users u "
                + "WHERE u.idUser=?1 AND "
                + "(EXISTS (SELECT i.fkCategory FROM Interest i WHERE i.fkUser=u AND i.fkCategory MEMBER OF c.categories)) AND r.fkCard=c "
                + "GROUP BY c "
                + "ORDER BY 2,3";
        //create query
        Query query = entityManager.createQuery(ejbQL);
        query.setParameter(1,idUser);
        //execute the query
        List<Object> result=query.getResultList();
        
        JsonArrayBuilder localComplexCards = Json.createArrayBuilder();
        JsonObjectBuilder complexCard;
        
        //every iteration is a result of the query made by an array of 3 elements: Card, interestNumber, avgRating
        Iterator itr = result.iterator();
        while(itr.hasNext()){
            Object[] obj = (Object[]) itr.next();
            System.out.println(obj.length);
            complexCard=((Card)obj[0]).toJsonObjectBuilder();
            complexCard.add("interestNumber",Integer.parseInt(String.valueOf(obj[1])));
            complexCard.add("avgRating",Float.parseFloat(String.valueOf(obj[2])));
            localComplexCards.add(complexCard.build());
        }
        
        return localComplexCards.build().toString();
    }

    //1) aggiunta nuova card con eventuale multimedia (card+cardCategory+Multimedia+User)
    //2) aggiunta nuovo commento (card+Comment+User)
    //3) aggiunta nuova valutazione (card+Rating+User)
    //4) (NON NECESSARIA) modifica della card
    //5) (NON NECESSARIA) rimuovere una card
    //***6) scaricare tutte le card di una determinata categoria in ordine di popolarità(card+cardCategory+Multimedia+Rating)
    //***7) scaricare la lista di card relativa ai followed legate alle categorie di interesse dell'user in ordine temporale(card+cardCategory+Multimedia+Rating+User)
    //***8) card più popolari (card+cardCategory+Multimedia+Rating)
    
}
