/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author luca_universita
 */
@Entity
public class Rating implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRating;

    @Column(nullable=false)
    private float value;
    
    @Column(nullable=false)
    private long timestamp;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fkUser",nullable=false)
    private Users fkUser;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fkCard",nullable=false)
    private Card fkCard;
    
    public Long getIdRating() {
        return idRating;
    }

    public void setIdRating(Long idRating) {
        this.idRating = idRating;
    }
    

    public Users getFkUser() {
        return fkUser;
    }

    public Card getFkCard() {
        return fkCard;
    }

    public void setFkCard(Card fkCard) {
        this.fkCard = fkCard;
    }

    public void setFkUser(Users fkUser) {
        this.fkUser = fkUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRating != null ? idRating.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rating)) {
            return false;
        }
        Rating other = (Rating) object;
        if ((this.idRating == null && other.idRating != null) || (this.idRating != null && !this.idRating.equals(other.idRating))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Interest[ id=" + idRating + " ]";
    }
    
}
