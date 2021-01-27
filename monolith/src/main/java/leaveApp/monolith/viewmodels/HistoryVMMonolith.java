/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.viewmodels;

/**
 *
 * @author Laila
 */
public class HistoryVMMonolith {
     String id, status, notes, manager, request, managerName;

    public HistoryVMMonolith() {
    }

    public HistoryVMMonolith(String id, String status, String notes, String manager, String request, String managerName) {
        this.id = id;
        this.status = status;
        this.notes = notes;
        this.manager = manager;
        this.request = request;
        this.managerName = managerName;
       
    }

    public HistoryVMMonolith(String id, String status, String notes, String manager, String request) {
        this.id = id;
        this.status = status;
        this.notes = notes;
        this.manager = manager;
        this.request = request;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

  
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
     
        

}
