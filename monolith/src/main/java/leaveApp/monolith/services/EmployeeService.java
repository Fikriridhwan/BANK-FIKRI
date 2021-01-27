/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import leaveApp.monolith.config.EmailServiceImpl;
import leaveApp.monolith.entities.Employee;
import leaveApp.monolith.entities.User;
import leaveApp.monolith.repositories.EmployeeRepository;
import leaveApp.monolith.viewmodels.RequestVMMonolith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

/**
 *
 * @author User
 */
@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;
    UserService userService;
//    EmailServiceImpl emailServiceImpl;
    EmailService emailService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, UserService userService, EmailService emailService) {
        this.employeeRepository = employeeRepository;
        this.userService = userService;
        this.emailService = emailService;
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getbyId(String id) {
        return employeeRepository.findById(id);
    }

    @Transactional
    public Employee save(Employee e) {
        System.out.println(e.toString());
        return employeeRepository.save(e);
    }

    public String delete(String id) {
        try {
            employeeRepository.deleteById(id);
            return "delete succes id:" + id;
        } catch (Exception e) {
            return "detele failed";
        }
    }

    public boolean sendEmail(Employee employee, User user) {
//        User user = userService.getbyId(e.getId()).get();
        String body = "please insert this code to verify: "+user.getCode();
        Map<String, Object> model = new HashMap<>();
        model.put("bodyEmail", body);

        boolean result  = emailService.sendEmailPassword(employee,model).isStatus();
        System.out.println(result);
        System.out.println(user.getCode());
        return result;
    }

    public boolean sendEmailRequest(RequestVMMonolith request, Employee employee, String subject, String body) {
//        String body = "please insert this code to verify: "+user.getCode();
        String subjectEmail = subject+" Cuti "+request.getLeaveDataName();
        Map<String, Object> model = new HashMap<>();
        model.put("bodyEmail", body);
         boolean result  = emailService.sendEmail(employee,subjectEmail,model).isStatus();
//        User user = userService.getbyId(e.getId()).get();   
//        emailServiceImpl.sendingEmail(requestVMMonolith, employee, subject, body);
//        System.out.println(user.getCode());
        return result;
    }

    public Employee getEmployeeById(String id) {
        return employeeRepository.getEmployeeById(id);
    }

    public void consumeEmployee(String id, String name, String email, String gender, String marital_status, String hire_date, String end_date, String religion, String manager) {
        employeeRepository.consumeemployee(id, name, email, gender, marital_status, hire_date, end_date, 12, religion, manager);
    }

    public void userInsert(String id, String username, String password) {
        String test = StringUtils.randomAlphanumeric(10).toUpperCase();
        System.out.println(test);
        employeeRepository.insertUser(id, username, password, test);
    }

}
