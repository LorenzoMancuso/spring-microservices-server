/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author lorenzo
 */
@Entity
public class Users implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    
    @Column(name = "username", nullable=false)
    private String username;
    
    @Column(name = "password", nullable=false)
    private String password;

    @Column(nullable=false)
    private String email;
    
    @Column(nullable=false)
    private String surname;
    
    @Column(nullable=false)
    private String name;
    
    @Column(nullable=false)
    private long birthDate;
    
    @Column(nullable=false)
    private String country;
    
    @Column(nullable=false)
    private String city;
    
    @Column(nullable=false)
    private String profession;
    
    @Column(nullable=false)
    private long subscriptionDate;
    
    // Oauth2 utility
    @Column(nullable = false)
    private boolean enabled;
    
    @Column(name = "profile_image")
    private String profileImage;

    @OneToMany(mappedBy = "fkUser")
    private List<Card> cards;
    
    @OneToMany(mappedBy = "fkUser")
    private List<Interest> categories;
    
    @OneToMany(mappedBy = "followed")
    private List<Follower> followers;
    
    @OneToMany(mappedBy = "follower")
    private List<Follower> followed;

    public void setCategories(List<Interest> categories) {
        this.categories = categories;
    }

    public List<Interest> getCategories() {
        return categories;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(long birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public long getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(long subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Follower> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Follower> followers) {
        this.followers = followers;
    }

    public List<Follower> getFollowed() {
        return followed;
    }

    public void setFollowed(List<Follower> followed) {
        this.followed = followed;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long id) {
        this.idUser = id;
    }
    
    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        JsonObjectBuilder obj=Json.createObjectBuilder()
            .add("idUser", idUser)
            .add("username", username)
            .add("email", email)
            .add("surname", surname)
            .add("name", name)
            .add("birthDate", birthDate)
            .add("country", country)
            .add("city", city)
            .add("profession", profession)
            .add("subscriptionDate", subscriptionDate);
        if(profileImage!=null)
            obj.add("profileImage",profileImage);
        
        JsonArrayBuilder localFollowers = Json.createArrayBuilder();
        for(Follower follower:followers){
            localFollowers.add(follower.getFollower().toJson());
        }
        obj.add("followers",localFollowers.build());
        
        JsonArrayBuilder localFollowed=Json.createArrayBuilder();
        for(Follower followed:followed){
            localFollowed.add(followed.getFollowed().toJson());
        }
        obj.add("followed",localFollowed.build());
        
        JsonArrayBuilder localInterests=Json.createArrayBuilder();
        for(Interest interest:categories){
            localInterests.add(interest.getFkCategory().toJson());
        }
        obj.add("interests",localInterests.build());
        
        return obj
            .build()
            .toString();
    }

    public JsonObject toJson() {
        return toJsonObjectBuilder()      
            .build();
    }    
    
    public JsonObjectBuilder toJsonObjectBuilder() {
        JsonObjectBuilder obj = Json.createObjectBuilder()
            .add("idUser", idUser)
            .add("username", username)
            .add("email", email)
            .add("surname", surname)
            .add("name", name)
            .add("birthDate", birthDate)
            .add("country", country)
            .add("city", city)
            .add("profession", profession)
            .add("subscriptionDate", subscriptionDate);
        if(profileImage!=null)
            obj.add("profileImage",profileImage);
        return obj;
    }
    
    public void addInterest(Interest interest){
        this.categories.add(interest);        
    }
    
    public void addFollowed(Follower followed){
        this.followed.add(followed);        
    }
    

    // Oauth2 utilities
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
            return true;
    }

    @Override
    public boolean isAccountNonLocked() {
            // we never lock accounts
            return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
            // credentials never expire
            return true;
    }

    @Override
    public boolean isEnabled() {
            return enabled;
    }
}
