/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author luca_universita
 */
@Entity
public class Card implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCard;
    
    @Column(nullable=false)
    private String description;
    
    @Column(nullable=false)
    private long timestamp;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fkUser",nullable=false)
    private Users fkUser;
    
    @OneToMany(mappedBy = "fkCard")
    private List<Comment> comments;
    
    @OneToMany(mappedBy = "fkCard")
    private List<Multimedia> multimedia;
    
    @OneToMany(mappedBy = "fkCard")
    private List<CardCategory> categories;
    
    @OneToMany(mappedBy = "fkCard")
    private List<Rating> ratings;
    
    
    public Users getFkUser() {
        return fkUser;
    }

    public void setFkUser(Users fkUser) {
        this.fkUser = fkUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Long getIdCard() {
        return idCard;
    }

    public void setIdCard(Long id) {
        this.idCard = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCard != null ? idCard.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Card)) {
            return false;
        }
        Card other = (Card) object;
        if ((this.idCard == null && other.idCard != null) || (this.idCard != null && !this.idCard.equals(other.idCard))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        JsonObjectBuilder obj = Json.createObjectBuilder()
            .add("idCard", idCard)
            .add("description", description)
            .add("timestamp", timestamp)
            .add("user", fkUser.toJson());
                
        JsonArrayBuilder localComments= Json.createArrayBuilder();
        for(Comment comment:comments){
            localComments.add(comment.toJson());
        }
        obj.add("comments",localComments.build());    

        JsonArrayBuilder localMultimedia= Json.createArrayBuilder();
        for(Multimedia mlt:multimedia){
            localMultimedia.add(mlt.toJson());
        }
        obj.add("multimedia",localMultimedia.build());    

        JsonArrayBuilder localCategories= Json.createArrayBuilder();
        for(CardCategory cardCategory:categories){
            localCategories.add(cardCategory.getFkCategory().toJson());
        }
        obj.add("categories",localCategories.build()); 

        JsonArrayBuilder localRatings= Json.createArrayBuilder();
        for(Rating rating:ratings){
            localRatings.add(rating.toJson());
        }
        obj.add("ratings",localRatings.build()); 
            
        return obj.build().toString();
    }
    
}
