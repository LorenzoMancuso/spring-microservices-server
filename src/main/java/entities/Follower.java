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
public class Follower implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idFollower;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="follower")
    private Long follower;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="followed")
    private Long followed;

    public Long getFollower() {
        return follower;
    }

    public void setFollower(Long follower) {
        this.follower = follower;
    }

    public Long getFollowed() {
        return followed;
    }

    public void setFollowed(Long followed) {
        this.followed = followed;
    }

    public Long getIdFollower() {
        return idFollower;
    }

    public void setIdFollower(Long id) {
        this.idFollower = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFollower != null ? idFollower.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Follower)) {
            return false;
        }
        Follower other = (Follower) object;
        if ((this.idFollower == null && other.idFollower != null) || (this.idFollower != null && !this.idFollower.equals(other.idFollower))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Follower[ id=" + idFollower + " ]";
    }
    
}
