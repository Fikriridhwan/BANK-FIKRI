/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.apiControllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import leaveApp.monolith.viewmodels.RequestVM;
import leaveApp.monolith.entities.Employee;
import leaveApp.monolith.entities.History;
import leaveApp.monolith.entities.LeaveData;
import leaveApp.monolith.entities.Request;
import leaveApp.monolith.services.EmployeeService;
import leaveApp.monolith.services.HistoryService;
import leaveApp.monolith.services.LeaveDataService;
import leaveApp.monolith.services.RequestService;
import leaveApp.monolith.viewmodels.EmployeeVM;
import leaveApp.monolith.viewmodels.HistoryVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
@RequestMapping("api")
@RestController
public class RequestApiController {

    RequestService service;
    LeaveDataService leaveService;
    EmployeeService employeeService;
    HistoryService historyService;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat StringFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public RequestApiController(RequestService service, LeaveDataService leaveDataService, EmployeeService employeeService, HistoryService historyService) {
        this.service = service;
        this.leaveService = leaveDataService;
        this.employeeService = employeeService;
        this.historyService = historyService;
    }

    @GetMapping("/request")
    public List<RequestVM> getAll() {
        List<Request> list = service.getAll();
        List<RequestVM> listVM = new ArrayList<>();
        for (Request request : list) {
            String startDate = dateFormat.format(request.getStartDate());
            String endDate = dateFormat.format(request.getEndDate());
            RequestVM vm = new RequestVM(request.getId(), request.getEmployee().getId(), request.getLeaveData().getId(), startDate, endDate, request.getApproval(), "");
            listVM.add(vm);
        }
        return listVM;
    }

    @GetMapping("/request/data/{id}")
    public List<RequestVM> getDataById(@PathVariable String id) {
        List<Request> list = service.getDataApproval(id, "concluded", "L01");
        List<RequestVM> listVM = new ArrayList<>();

        for (Request request : list) {
            String startDate = dateFormat.format(request.getStartDate());
            String endDate = dateFormat.format(request.getEndDate());
            LeaveData leaveData = leaveService.getById(request.getLeaveData().getId()).get();
            RequestVM vm = new RequestVM(request.getId(), request.getEmployee().getId(), leaveData.getId() + ": " + leaveData.getName(), startDate, endDate, request.getApproval(), "");
            listVM.add(vm);
        }
        return listVM;
    }

    @GetMapping("/request/spesial/{id}")
    public List<RequestVM> getDataRequestSpesial(@PathVariable String id) {
        List<Request> list = service.getDataRequestSpesial(id, "concluded", "L01");
        List<RequestVM> listVM = new ArrayList<>();

        for (Request request : list) {
            String startDate = dateFormat.format(request.getStartDate());
            String endDate = dateFormat.format(request.getEndDate());
            LeaveData leaveData = leaveService.getById(request.getLeaveData().getId()).get();
            RequestVM vm = new RequestVM(request.getId(), request.getEmployee().getId(),  leaveData.getName(), startDate, endDate, request.getApproval(), "");
            listVM.add(vm);
        }
        return listVM;
    }

    @GetMapping("/request/{id}")
    public RequestVM getById(@PathVariable String id) {
        System.out.println(id);
        Request request = service.getById(id).get();
        String startDate = dateFormat.format(request.getStartDate());
        String endDate = dateFormat.format(request.getEndDate());
        RequestVM v = new RequestVM(request.getId(), request.getEmployee().getId(), request.getLeaveData().getId(), startDate, endDate, request.getApproval(), "");
        return v;
    }

    @PostMapping("/request")
    public RequestVM insert(@RequestBody RequestVM data) {
        try {
            Date startDate = StringFormat.parse(data.getStartDate());
            Date endDate = StringFormat.parse(data.getEndDate());
            Request request = new Request(data.getId(), startDate, endDate, data.getApproval(), new Employee(data.getEmployee()), new LeaveData(data.getLeave()));
            Request result = service.save(request);
            String startDate1 = dateFormat.format(result.getStartDate());
            String endDate1 = dateFormat.format(result.getEndDate());
            RequestVM resultVM = new RequestVM(result.getId(), result.getEmployee().getId(), result.getLeaveData().getId(), startDate1, endDate1, result.getApproval(), "");
            return resultVM;
        } catch (ParseException ex) {
            Logger.getLogger(RequestApiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @PostMapping("/requestspecial")
    public RequestVM insertSpecial(@RequestBody RequestVM requestVM) {
        LeaveData leaveData = leaveService.getById(requestVM.getLeave()).get();
        System.out.println(leaveData.getName());
        if (leaveData.getName().equals("Haji")) {
            if (!service.getByLeaveData(new LeaveData(requestVM.getLeave())).isPresent()) {
                try {
                    Date startDate = StringFormat.parse(requestVM.getStartDate());
                    Date endDate = StringFormat.parse(requestVM.getEndDate());
                    Request request = new Request(requestVM.getId(), startDate, endDate, requestVM.getApproval(), new Employee(requestVM.getEmployee()), new LeaveData(requestVM.getLeave()));
                    Request result = service.save(request);
                    String startDate1 = dateFormat.format(result.getStartDate());
                    String endDate1 = dateFormat.format(result.getEndDate());
                    RequestVM resultVM = new RequestVM(result.getId(), result.getEmployee().getId(), result.getLeaveData().getId(), startDate1, endDate1, result.getApproval(), "");
                    return resultVM;
                } catch (ParseException ex) {
                    Logger.getLogger(RequestApiController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                Date startDate = StringFormat.parse(requestVM.getStartDate());
                Date endDate = StringFormat.parse(requestVM.getEndDate());
                Request request = new Request(requestVM.getId(), startDate, endDate, requestVM.getApproval(), new Employee(requestVM.getEmployee()), new LeaveData(requestVM.getLeave()));
                Request result = service.save(request);
                String startDate1 = dateFormat.format(result.getStartDate());
                String endDate1 = dateFormat.format(result.getEndDate());
                RequestVM resultVM = new RequestVM(result.getId(), result.getEmployee().getId(), result.getLeaveData().getId(), startDate1, endDate1, result.getApproval(), "");
                return resultVM;
            } catch (ParseException ex) {
                Logger.getLogger(RequestApiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

//    @PutMapping("")
//    public boolean update(@RequestBody Request request){
//        return service.updatePerson(request);
//    }
    @DeleteMapping("/request/{id}")
    public Map<String, String> delete(@PathVariable String id) {
        boolean isSuccess = service.delete(id);
        Map<String, String> status = new HashMap<>();
        if (isSuccess) {
            status.put("status", "200");
            return status;
        } else {
            return status;
        }
    }

    //    ---------------------LeaveData---------------------------------
    @GetMapping("/request/leavedataspecial/get")
    public List<LeaveData> getAllLeave() {
//        System.out.println(leaveService.spesialLeave("L01"));
        return leaveService.spesialLeave("L01");
    }

    @GetMapping("/leave/getById/{id}")
    public LeaveData getLeaveById(@PathVariable String id) {
        return leaveService.getById(id).get();
    }

    @PostMapping("/request/leavedata/save")
    public LeaveData leaveInsert(@RequestBody LeaveData leaveData) {
        return leaveService.save(leaveData);
    }

    @DeleteMapping("/request/leavedata/{id}")
    public boolean leaveDelete(@PathVariable String id) {
        return leaveService.delete(id);
    }

    @ResponseBody
    @GetMapping("/request/leavedata/getById/{id}")
    public LeaveData leaveGetById(@PathVariable String id) {
        return leaveService.getById(id).get();
    }

    @PutMapping("/request/leavedata/update")
    public boolean update(@RequestBody LeaveData leaveData) {
        return leaveService.update(leaveData);
    }

    //    ---------------------Employee---------------------------------
    @GetMapping("/employee/getById/{id}")
    public EmployeeVM employeeGetById(@PathVariable String id) {
        Employee e = employeeService.getbyId(id).get();
        String hireDate = dateFormat.format(e.getHireDate());
        String endDate = dateFormat.format(e.getEndDate());
        EmployeeVM result = new EmployeeVM(e.getId(), e.getName(), e.getEmail(),
                e.getGender(), e.getMaritalStatus(), hireDate, endDate, e.getReligion(),
                e.getManager().getId(), e.getQuota());
        return result;
    }

    @PostMapping("/employee/save")
    public EmployeeVM employeeSave(@RequestBody EmployeeVM employeeVM) {
        Employee e = employeeService.getbyId(employeeVM.getId()).get();
        e.setQuota(employeeVM.getQuota());
        Employee empSave = employeeService.save(e);
        String hireDate = dateFormat.format(empSave.getHireDate());
        String endDate = dateFormat.format(empSave.getEndDate());
        EmployeeVM result = new EmployeeVM(empSave.getId(), empSave.getName(), empSave.getEmail(),
                empSave.getGender(), empSave.getMaritalStatus(), hireDate, endDate, empSave.getReligion(),
                empSave.getManager().getId(), empSave.getQuota());
        return result;
    }

    //    ---------------------History---------------------------------
    @GetMapping("/history/approved/get")
    public List<HistoryVM> getAllHistoryApproved() {
        List<History> list = historyService.getAllByStatus("approved");
        List<HistoryVM> historyVM = new ArrayList<>();
        for (History history : list) {
            Employee emp = employeeService.getbyId(history.getManager().getId()).get();
            Request req = service.getById(history.getRequest().getId()).get();
            HistoryVM vm = new HistoryVM(history.getId(), history.getStatus(), history.getNotes(), emp.getName(), req.getLeaveData().getName());
            historyVM.add(vm);
        }
        return historyVM;
    }
    
    @GetMapping("/history/reject/get")
    public List<HistoryVM> getAllHistoryReject() {
        List<History> list = historyService.getAllByStatus("rejected");
        List<HistoryVM> historyVM = new ArrayList<>();
        for (History history : list) {
            Employee emp = employeeService.getbyId(history.getManager().getId()).get();
            Request req = service.getById(history.getRequest().getId()).get();
            HistoryVM vm = new HistoryVM(history.getId(), history.getStatus(), history.getNotes(), emp.getName(), req.getLeaveData().getName());
            historyVM.add(vm);
        }
        return historyVM;
    }
    
    @GetMapping("/history/getById/{id}")
    public HistoryVM historyGetById(@PathVariable String id) {
        History e = historyService.getById(id).get();
        HistoryVM result = new HistoryVM(e.getId(), e.getStatus(), e.getNotes(), e.getManager().getId(), e.getRequest().getId());
        return result;
    }

}
