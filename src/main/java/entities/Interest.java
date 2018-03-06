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
public class Interest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idInterest;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fkUser")
    private Long fkUser;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fkCategory")
    private Long fkCategory;

    public Long getFkUser() {
        return fkUser;
    }

    public void setFkUser(Long fkUser) {
        this.fkUser = fkUser;
    }

    public Long getFkCategory() {
        return fkCategory;
    }

    public void setFkCategory(Long fkCategory) {
        this.fkCategory = fkCategory;
    }

    public Long getIdInterest() {
        return idInterest;
    }

    public void setIdInterest(Long id) {
        this.idInterest = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInterest != null ? idInterest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Interest)) {
            return false;
        }
        Interest other = (Interest) object;
        if ((this.idInterest == null && other.idInterest != null) || (this.idInterest != null && !this.idInterest.equals(other.idInterest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Interest[ id=" + idInterest + " ]";
    }
    
}
