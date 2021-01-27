/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import leaveApp.monolith.entities.Employee;
import leaveApp.monolith.entities.History;
import leaveApp.monolith.entities.Request;
import leaveApp.monolith.repositories.RequestRepository;
import leaveApp.monolith.services.EmployeeService;
import leaveApp.monolith.services.HistoryService;
import leaveApp.monolith.services.RequestService;
import leaveApp.monolith.viewmodels.HistoryVM;
import leaveApp.monolith.viewmodels.HistoryVMMonolith;
import leaveApp.monolith.viewmodels.RequestVM;
import leaveApp.monolith.viewmodels.RequestVMMonolith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Laila
 */
@Controller
public class RequestController {

    @Autowired
    private RequestService requestService;
    @Autowired
    private HistoryService historyService;

    @Autowired
    RequestRepository requestRepository;
    @Autowired
    EmployeeService employeeService;

    @ResponseBody
    @RequestMapping("/getByEmployeeId/{employeeId}")
    public List<Request> getByEmployeeId(@PathVariable String employeeId) {

        return requestService.getByEmployeeId(employeeId);
    }

//---------------------------------------PIC------------------------------------------
    @GetMapping("/pic")
    public String pic(Model model) {
        model.addAttribute("requests", requestService.getAllLevel1());
        model.addAttribute("request", new RequestVM());
        return "picView";
    }

    @GetMapping("/piclevel2")
    public String picLevel1(Model model) {
        model.addAttribute("requests", requestService.getAllLevel2());
        model.addAttribute("request", new RequestVM());
        return "picView1";
    }

    @GetMapping("/redirectpic")
    public String repicLevel2() {
        return "redirect:/piclevel2";
    }

    @GetMapping("/redirectpic1")
    public String repicLevel22() {
        return "redirect:/redirectpic";
    }

    @GetMapping("/redirectpicreject")
    public String repicConcluded() {
        return "redirect:/picConcluded";
    }

    @GetMapping("/picConcluded")
    public String picConcluded(Model model) {
        model.addAttribute("requests", requestService.getAllConcluded());
        model.addAttribute("request", new RequestVM());
        return "picView1";
    }

    @GetMapping("/picHistory")
    public String picHistory(Model model) {
        model.addAttribute("requests", historyService.getAllMonolith());
        model.addAttribute("request", new HistoryVM());
        return "historyView";
    }
    //acceptrequest

    @ResponseBody
    @GetMapping("/pic/acceptrequest")
    public RequestVMMonolith picApprove(String id) {
        requestService.picApprove(id);
        Request request = requestService.getById(id).get();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date hire = request.getStartDate();
        String hiredate = dateFormat.format(hire);
        Date end = request.getEndDate();
        String enddate = dateFormat.format(end);
        RequestVMMonolith rvm = new RequestVMMonolith(id, request.getEmployee().getId(),
                request.getApproval(), hiredate, enddate,
                request.getLeaveData().getId(), "level 1= approved", "apa",
                request.getEmployee().getName(), request.getLeaveData().getName());

        return rvm;
    }

    //acceptrequest
    @ResponseBody
    @PostMapping("/pic/rejectrequest")
    public RequestVMMonolith picReject(Model model, String id, String notes) {
        model.addAttribute("request", new Request());
        requestService.picReject(id);
        Request request = requestService.getById(id).get();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date hire = request.getStartDate();
        String hiredate = dateFormat.format(hire);
        Date end = request.getEndDate();
        String enddate = dateFormat.format(end);
        RequestVMMonolith rvm = new RequestVMMonolith(id, request.getEmployee().getId(),
                request.getApproval(), hiredate, enddate,
                request.getLeaveData().getId(), "level 1= rejected", notes,
                request.getEmployee().getName(), request.getLeaveData().getName());
//        System.out.println(rvm.getNotes());
//        System.out.println(rvm.getApproval());
        HistoryVMMonolith hVM = new HistoryVMMonolith(id, "Rejected", notes, request.getEmployee().getManager().getId(), request.getId());
        Employee e = new Employee(hVM.getManager());
        History history = new History(hVM.getId(), hVM.getStatus(), notes, e, request);
        historyService.save(history);
        String body = "Pengajuan cuti "+rvm.getLeaveDataName()+" anda telah ditolak, mohon periksa di aplikasi";
         Employee employee = employeeService.getbyId(request.getEmployee().getId()).get();
        employeeService.sendEmailRequest(rvm, employee, "Rejection notification", body);
        return rvm;
    }

    @ResponseBody
    @GetMapping("/pic/getById")
    public RequestVMMonolith getById(@Valid String id) {
//    Optional<Request> request= requestRepository.findAllById(id);
//request.addAttribute("request", new Request()); 
        Request request = requestService.getById(id).get();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start = request.getStartDate();
        String startdate = dateFormat.format(start);
        Date end = request.getEndDate();
        String enddate = dateFormat.format(end);
        RequestVMMonolith rvm = new RequestVMMonolith(id, request.getEmployee().getId(),
                request.getApproval(), startdate, enddate, request.getLeaveData().getId());
        return rvm;
//     return "index";
//    if (request.isPresent()) {
//      return new ResponseEntity<>(request.get(), HttpStatus.OK);
//    } else {
//      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
    }

//-----------------------Manager--------------------------------
    @GetMapping("/manager")
    public String manager(Model model) {
        model.addAttribute("requests", requestService.getAllLevel2());
        model.addAttribute("request", new RequestVM());
        return "managerView";
    }

    @GetMapping("/managerConcluded")
    public String managerRejected(Model model) {
        model.addAttribute("requests", historyService.getAll());
        model.addAttribute("request", new RequestVM());
        return "historyView";
    }

    @GetMapping("/managerHistory")
    public String managerHistory(Model model) {
        model.addAttribute("requests", historyService.getAllMonolith());
        model.addAttribute("request", new RequestVM());
        return "historyView1";
    }

    @GetMapping("/redirectmanager")
    public String reManager() {
        return "redirect:/managerHistory";
    }

    //acceptrequest
    @ResponseBody
    @PostMapping("/manager/acceptrequest")
    public RequestVMMonolith managerApprove(String id, String notes) {
        requestService.managerAction(id);
        Request request = requestService.getById(id).get();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date hire = request.getStartDate();
        String hiredate = dateFormat.format(hire);
        Date end = request.getEndDate();
        String enddate = dateFormat.format(end);
        RequestVMMonolith rvm = new RequestVMMonolith(id, request.getEmployee().getId(),
                request.getApproval(), hiredate, enddate,
                request.getLeaveData().getId(), "Approved", notes,
                request.getEmployee().getName(), request.getLeaveData().getName());
        HistoryVM hVM = new HistoryVM(id, "Approved", notes, request.getEmployee().getManager().getId(), request.getId());
        Employee e = new Employee(hVM.getManager());
        Employee employee = employeeService.getbyId(request.getEmployee().getId()).get();
        History history = new History(hVM.getId(), hVM.getStatus(), notes, e, request);
        historyService.save(history);
        String body = "Pengajuan "+rvm.getLeaveDataName()+" anda telah diterima, mohon periksa di aplikasi";
        employeeService.sendEmailRequest(rvm, employee, "Notifikasi pengajuan", body);
        return rvm;
    }
    

    //acceptrequest
    @ResponseBody
    @PostMapping("/manager/rejectrequest")
    public RequestVMMonolith managerReject(Model model, String id, String notes) {
        model.addAttribute("request", new Request());
        requestService.managerAction(id);
        Request request = requestService.getById(id).get();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date hire = request.getStartDate();
        String hiredate = dateFormat.format(hire);
        Date end = request.getEndDate();
        String enddate = dateFormat.format(end);
        RequestVMMonolith rvm = new RequestVMMonolith(id, request.getEmployee().getId(),
                request.getApproval(), hiredate, enddate,
                request.getLeaveData().getId(), "Rejected", notes,
                request.getEmployee().getName(), request.getLeaveData().getName());
        HistoryVM hVM = new HistoryVM(id, "Rejected", notes, request.getEmployee().getManager().getId(), request.getId());
        Employee e = new Employee(hVM.getManager());
        Employee employee = employeeService.getbyId(request.getEmployee().getId()).get();
        History history = new History(hVM.getId(), hVM.getStatus(), notes, e, request);
        historyService.save(history);
        String body = "Pengajuan cuti "+rvm.getLeaveDataName()+" anda telah ditolak, mohon periksa di aplikasi";
        employeeService.sendEmailRequest(rvm, employee, "Notifikasi pengajuan", body);
        return rvm;
    }
}
