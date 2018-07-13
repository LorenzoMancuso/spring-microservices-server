/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author lorenzo
 */
@Entity
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    
    @Column(nullable=false)
    private String username;

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
    
    @OneToMany(mappedBy = "fkUser")
    private List<Card> cards;
    
    @OneToMany(mappedBy = "fkUser")
    private List<Interest> categories;

    public void setCategories(List<Interest> categories) {
        this.categories = categories;
    }
    
    public void addInterest(Interest interest){
        this.categories.add(interest);        
    }

    public List<Interest> getCategories() {
        return categories;
    }

    @OneToMany(mappedBy = "follower")
    private List<Follower> followers;
    
    @OneToMany(mappedBy = "followed")
    private List<Follower> followed;
    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long id) {
        this.idUser = id;
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
        return "entities.UserEntity[ id=" + idUser + " ]";
    }
    
}
