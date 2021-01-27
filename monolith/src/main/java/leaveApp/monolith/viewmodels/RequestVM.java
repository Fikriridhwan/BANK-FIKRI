/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.viewmodels;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author User
 */
public class RequestVM {
    String id;
    String employee;
    String leave;
    String startDate;
    String endDate;
    String approval;
    String notes;

    public RequestVM() {
    }
    
    public RequestVM(String id, String employee, String leave, String startDate, String endDate, String approval, String notes) {
        this.id = id;
        this.employee = employee;
        this.leave = leave;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approval = approval;
        this.notes = notes;
    }

    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getLeave() {
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = leave;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    
}
