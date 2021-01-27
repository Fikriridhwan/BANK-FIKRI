/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.viewmodels;

/**
 *
 * @author User
 */
public class EmployeeVM {
    String id, name, email, gender, maritalStatus, hireDate, endDate, religion,manager;
    int quota;

    public EmployeeVM() {
    }

    public EmployeeVM(String id) {
        this.id = id;
    }

    public EmployeeVM(String id, String name, String email, String gender, String maritalStatus, String hireDate, String endDate, String religion, String manager, int quota) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.hireDate = hireDate;
        this.endDate = endDate;
        this.religion = religion;
        this.manager = manager;
        this.quota = quota;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }
    
    
}
