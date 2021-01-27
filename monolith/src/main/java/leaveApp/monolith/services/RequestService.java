/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import leaveApp.monolith.entities.Employee;
import leaveApp.monolith.entities.LeaveData;
import leaveApp.monolith.entities.Request;
import leaveApp.monolith.repositories.RequestRepository;
import leaveApp.monolith.viewmodels.RequestVMMonolith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Laila
 */
@Service
public class RequestService {

    RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    //read
    public List<Request> getAll() {
        return requestRepository.findAll();
    }

    public List<Request> getDataEmployee(String id) {
        return requestRepository.findByEmployee(new Employee(id));
    }

    public List<Request> getDataApproval(String id, String approval, String leaveId) {
        return requestRepository.findByApprovalNotAndEmployeeAndLeaveData(approval, new Employee(id), new LeaveData(leaveId));
    }

    public List<Request> getDataRequestSpesial(String id, String approval, String leaveId) {
        return requestRepository.findByApprovalNotAndEmployeeAndLeaveDataNot(approval, new Employee(id), new LeaveData(leaveId));
    }

    public Optional<Request> getById(String id) {
        return requestRepository.findById(id);
    }

    public Optional<Request> getByLeaveData(LeaveData leaveData) {
        return requestRepository.findByLeaveData(leaveData);
    }

    @Transactional
    public Request save(Request e) {
        return requestRepository.save(e);
    }

    public boolean delete(String id) {
        boolean requestCheck = getById(id).isPresent();
        if (requestCheck) {
            requestRepository.delete(getById(id).get());
            return true;
        }
        return false;
    }

    public List<Request> getByEmployeeId(String employeeId) {

        return requestRepository.findByEmployee(new Employee(employeeId));
    }

    public boolean update(Request request) {
        Optional<Request> requestOptional = getById(request.getId());
        if (requestOptional.isPresent()) {
            requestOptional.get().setApproval(request.getApproval());
            requestOptional.get().setEmployee(request.getEmployee());
            requestOptional.get().setEndDate(request.getEndDate());
            requestOptional.get().setStartDate(request.getStartDate());
            requestOptional.get().setLeaveData(request.getLeaveData());
            requestRepository.save(requestOptional.get());
            return true;
        }
        return false;
    }
//   -----------------------------------PIC--------------------------------

    public void picApprove(String id) {
        requestRepository.picApprove(id);
    }

    public void picReject(String id) {
        requestRepository.picReject(id);
    }

    public List<RequestVMMonolith> getAllLevel1() {
        List<RequestVMMonolith> vMs = new ArrayList<>();
        List<Request> requests = requestRepository.getAllLevel1();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        for (Request request : requests) {
            Date start = request.getStartDate();
            Date end = request.getEndDate();

            String startdate = dateFormat.format(start);
            String enddate = dateFormat.format(end);

            vMs.add(new RequestVMMonolith(
                    request.getId(), request.getEmployee().getId(),
                    request.getApproval(), startdate, enddate,
                    request.getLeaveData().getId(),
                    request.getEmployee().getName(),
                    request.getLeaveData().getName()));

        }
        return vMs;
    }

    public List<RequestVMMonolith> getAllLevel2() {
        List<RequestVMMonolith> vMs = new ArrayList<>();
        List<Request> requests = requestRepository.getAllLevel2();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        for (Request request : requests) {
            Date start = request.getStartDate();
            Date end = request.getEndDate();

            String startdate = dateFormat.format(start);
            String enddate = dateFormat.format(end);

            vMs.add(new RequestVMMonolith(
                    request.getId(), request.getEmployee().getId(),
                    request.getApproval(), startdate, enddate,
                    request.getLeaveData().getId(),
                    request.getEmployee().getName(),
                    request.getLeaveData().getName()));

        }
        return vMs;
    }

    public List<RequestVMMonolith> getAllConcluded() {
        List<RequestVMMonolith> vMs = new ArrayList<>();
        List<Request> requests = requestRepository.getAllConcluded();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        for (Request request : requests) {
            Date start = request.getStartDate();
            Date end = request.getEndDate();

            String startdate = dateFormat.format(start);
            String enddate = dateFormat.format(end);

            vMs.add(new RequestVMMonolith(
                    request.getId(), request.getEmployee().getId(),
                    request.getApproval(), startdate, enddate,
                    request.getLeaveData().getId(),
                    request.getEmployee().getName(),
                    request.getLeaveData().getName()));

        }
        return vMs;
    }

    public List<RequestVMMonolith> getAllConcludedRejected() {
        List<RequestVMMonolith> vMs = new ArrayList<>();
        List<Request> requests = requestRepository.getAllConcluded();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        for (Request request : requests) {
            Date start = request.getStartDate();
            Date end = request.getEndDate();

            String startdate = dateFormat.format(start);
            String enddate = dateFormat.format(end);

            vMs.add(new RequestVMMonolith(
                    request.getId(), request.getEmployee().getId(),
                    request.getApproval(), startdate, enddate,
                    request.getLeaveData().getId(),
                    request.getEmployee().getName(),
                    request.getLeaveData().getName()));

        }
        return vMs;
    }

//    ---------------------------manager---------------------------
    public void managerAction(String id) {
        requestRepository.managerAction(id);
    }

}
