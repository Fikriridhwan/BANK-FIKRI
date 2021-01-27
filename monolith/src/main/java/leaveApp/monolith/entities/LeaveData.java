/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "leave_data")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LeaveData.findAll", query = "SELECT l FROM LeaveData l")
    , @NamedQuery(name = "LeaveData.findById", query = "SELECT l FROM LeaveData l WHERE l.id = :id")
    , @NamedQuery(name = "LeaveData.findByName", query = "SELECT l FROM LeaveData l WHERE l.name = :name")
    , @NamedQuery(name = "LeaveData.findByDuration", query = "SELECT l FROM LeaveData l WHERE l.duration = :duration")})
public class LeaveData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "duration")
    private Integer duration;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "leaveData", fetch = FetchType.LAZY)
    private List<Request> requestList;

    public LeaveData() {
    }

    public LeaveData(String id) {
        this.id = id;
    }

    public LeaveData(String id, String name, Integer duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @XmlTransient
    @JsonIgnore
    public List<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LeaveData)) {
            return false;
        }
        LeaveData other = (LeaveData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "leaveApp.monolith.entities.LeaveData[ id=" + id + " ]";
    }
    
}
