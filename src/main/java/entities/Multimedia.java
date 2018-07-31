/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 *
 * @author luca_universita
 */
@Entity
public class Multimedia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMultimedia;
    
    @Lob
    @Column
    private byte[] data;
    
    @Column(nullable=false)
    private String link;
    
    @Column(nullable=false)
    private String type;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fkCard",nullable=false)
    private Card fkCard;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Card getFkCard() {
        return fkCard;
    }

    public void setFkCard(Card fkCard) {
        this.fkCard = fkCard;
    }
    
    public Long getIdMultimedia() {
        return idMultimedia;
    }

    public void setIdMultimedia(Long id) {
        this.idMultimedia = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMultimedia != null ? idMultimedia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Multimedia)) {
            return false;
        }
        Multimedia other = (Multimedia) object;
        if ((this.idMultimedia == null && other.idMultimedia != null) || (this.idMultimedia != null && !this.idMultimedia.equals(other.idMultimedia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return toJson().toString();
    }
    
    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("idMultimedia", idMultimedia)
            .add("link", link)
            .add("type", type)
            .build();
    }
    
}
