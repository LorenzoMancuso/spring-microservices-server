/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
public class CardCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCardCategory;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fkCard")
    private Long fkCard;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fkCategory")
    private Long fkCategory;

    public Long getFkCard() {
        return fkCard;
    }

    public void setFkCard(Long fkCard) {
        this.fkCard = fkCard;
    }

    public Long getFkCategory() {
        return fkCategory;
    }

    public void setFkCategory(Long fkCategory) {
        this.fkCategory = fkCategory;
    }

    public Long getIdCardCategory() {
        return idCardCategory;
    }

    public void setIdCardCategory(Long id) {
        this.idCardCategory = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCardCategory != null ? idCardCategory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CardCategory)) {
            return false;
        }
        CardCategory other = (CardCategory) object;
        if ((this.idCardCategory == null && other.idCardCategory != null) || (this.idCardCategory != null && !this.idCardCategory.equals(other.idCardCategory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CardCategory[ id=" + idCardCategory + " ]";
    }
    
}
