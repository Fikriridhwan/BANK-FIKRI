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
public class RequestVMMonolith {
    String id,employee,approval, startDate, endDate, leaveData, status, notes, employeeName, leaveDataName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RequestVMMonolith() {
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

    public String getLeaveData() {
        return leaveData;
    }

    public void setLeaveData(String leaveData) {
        this.leaveData = leaveData;
    }

    public String getStatus() {
        return status;
    }

    public RequestVMMonolith(String id, String employee, String approval, String startDate, String endDate, String leaveData, String employeeName, String leaveDataName) {
        this.id = id;
        this.employee = employee;
        this.approval = approval;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveData = leaveData;
        this.employeeName = employeeName;
        this.leaveDataName = leaveDataName;
    }

    public RequestVMMonolith(String id, String employee, String approval, String startDate, String endDate, String leaveData, String status, String notes, String employeeName, String leaveDataName) {
        this.id = id;
        this.employee = employee;
        this.approval = approval;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveData = leaveData;
        this.status = status;
        this.notes = notes;
        this.employeeName = employeeName;
        this.leaveDataName = leaveDataName;
    }

    public RequestVMMonolith(String id, String status, String notes, String employee, String approval, String startDate, String endDate) {
        this.id = id;
        this.status = status;
        this.notes = notes;
        this.employee = employee;
        this.approval = approval;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public RequestVMMonolith(String id, String employee, String approval, String startDate, String endDate, String leaveData) {
        this.id = id;
        this.employee = employee;
        this.approval = approval;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveData = leaveData;
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

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getLeaveDataName() {
        return leaveDataName;
    }

    public void setLeaveDataName(String leaveDataName) {
        this.leaveDataName = leaveDataName;
    }

}
