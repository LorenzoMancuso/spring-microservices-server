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
import javax.persistence.OneToOne;

/**
 *
 * @author luca_universita
 */
@Entity
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComment;
    
    @Column(nullable=false)
    private String text;
    
    @Column(nullable=false)
    private long timestamp;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fkCard",nullable=false)
    private Card fkCard;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fkUser",nullable=false)
    private Users fkUser;

    public Card getFkCard() {
        return fkCard;
    }

    public void setFkCard(Card fkCard) {
        this.fkCard = fkCard;
    }

    public Users getFkUser() {
        return fkUser;
    }

    public void setFkUser(Users fkUser) {
        this.fkUser = fkUser;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long id) {
        this.idComment = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComment != null ? idComment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.idComment == null && other.idComment != null) || (this.idComment != null && !this.idComment.equals(other.idComment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Comment[ id=" + idComment + " ]";
    }
    
}
