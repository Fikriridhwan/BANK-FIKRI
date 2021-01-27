/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "employee2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee2.findAll", query = "SELECT e FROM Employee2 e")
    , @NamedQuery(name = "Employee2.findById", query = "SELECT e FROM Employee2 e WHERE e.id = :id")
    , @NamedQuery(name = "Employee2.findByName", query = "SELECT e FROM Employee2 e WHERE e.name = :name")
    , @NamedQuery(name = "Employee2.findByHireDate", query = "SELECT e FROM Employee2 e WHERE e.hireDate = :hireDate")
    , @NamedQuery(name = "Employee2.findByEndDate", query = "SELECT e FROM Employee2 e WHERE e.endDate = :endDate")
    , @NamedQuery(name = "Employee2.findBySalary", query = "SELECT e FROM Employee2 e WHERE e.salary = :salary")
    , @NamedQuery(name = "Employee2.findByGender", query = "SELECT e FROM Employee2 e WHERE e.gender = :gender")
    , @NamedQuery(name = "Employee2.findByMaritalStatus", query = "SELECT e FROM Employee2 e WHERE e.maritalStatus = :maritalStatus")
    , @NamedQuery(name = "Employee2.findByManager", query = "SELECT e FROM Employee2 e WHERE e.manager = :manager")
    , @NamedQuery(name = "Employee2.findByReligion", query = "SELECT e FROM Employee2 e WHERE e.religion = :religion")
    , @NamedQuery(name = "Employee2.findByBirthDate", query = "SELECT e FROM Employee2 e WHERE e.birthDate = :birthDate")
    , @NamedQuery(name = "Employee2.findByUniversity", query = "SELECT e FROM Employee2 e WHERE e.university = :university")})
public class Employee2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "hire_date")
    @Temporal(TemporalType.DATE)
    private Date hireDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "salary")
    private Integer salary;
    @Column(name = "gender")
    private String gender;
    @Column(name = "marital_status")
    private String maritalStatus;
    @Column(name = "manager")
    private String manager;
    @Column(name = "religion")
    private String religion;
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(name = "university")
    private String university;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employee2", fetch = FetchType.LAZY)
    private Contact contact;

    public Employee2() {
    }

    public Employee2(String id) {
        this.id = id;
    }

    public Employee2(String id, String name, Date hireDate, Date endDate, Integer salary, String gender, String maritalStatus, String manager, String religion, Date birthDate, String university, Contact contact) {
        this.id = id;
        this.name = name;
        this.hireDate = hireDate;
        this.endDate = endDate;
        this.salary = salary;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.manager = manager;
        this.religion = religion;
        this.birthDate = birthDate;
        this.university = university;
        this.contact = contact;
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

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
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
        if (!(object instanceof Employee2)) {
            return false;
        }
        Employee2 other = (Employee2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "leaveApp.monolith.entities.Employee2[ id=" + id + " ]";
    }
    
}
