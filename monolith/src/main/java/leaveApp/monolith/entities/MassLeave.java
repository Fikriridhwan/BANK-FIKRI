/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "mass_leave")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MassLeave.findAll", query = "SELECT m FROM MassLeave m")
    , @NamedQuery(name = "MassLeave.findById", query = "SELECT m FROM MassLeave m WHERE m.id = :id")
    , @NamedQuery(name = "MassLeave.findByName", query = "SELECT m FROM MassLeave m WHERE m.name = :name")
    , @NamedQuery(name = "MassLeave.findByStartDate", query = "SELECT m FROM MassLeave m WHERE m.startDate = :startDate")
    , @NamedQuery(name = "MassLeave.findByEndDate", query = "SELECT m FROM MassLeave m WHERE m.endDate = :endDate")})
public class MassLeave implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Basic(optional = false)
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    public MassLeave() {
    }

    public MassLeave(String id) {
        this.id = id;
    }

    public MassLeave(String id, String name, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
        if (!(object instanceof MassLeave)) {
            return false;
        }
        MassLeave other = (MassLeave) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "leaveApp.monolith.entities.MassLeave[ id=" + id + " ]";
    }
    
}
