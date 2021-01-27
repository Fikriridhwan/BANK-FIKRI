/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import leaveApp.monolith.viewmodels.UserVM;
import leaveApp.monolith.config.AuthenticationRequest;
import leaveApp.monolith.config.UserRefrence;
import leaveApp.monolith.entities.Employee;
import leaveApp.monolith.entities.Role;
import leaveApp.monolith.entities.User;
import leaveApp.monolith.entities.UserRole;
import leaveApp.monolith.services.EmployeeService;
import leaveApp.monolith.services.RoleService;
import leaveApp.monolith.config.UserRefrenceService;
import leaveApp.monolith.entities.Contact;
import leaveApp.monolith.entities.Employee2;
import leaveApp.monolith.services.ContactService;
import leaveApp.monolith.services.Employee2Service;
import leaveApp.monolith.services.UserRoleService;
import leaveApp.monolith.services.UserService;
import leaveApp.monolith.viewmodels.EmployeeVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author User
 */
@Controller
public class UserController {

    UserService service;
    EmployeeService employeeService;
    UserRoleService urService;
    Employee2Service employee2Service;
    ContactService contactService;
    UserRefrenceService refrenceService;
    AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService service, EmployeeService employeeService, UserRoleService urService, Employee2Service employee2Service, ContactService contactService, AuthenticationManager authenticationManager, UserRefrenceService refrenceService) {
        this.service = service;
        this.employeeService = employeeService;
        this.urService = urService;
        this.employee2Service = employee2Service;
        this.contactService = contactService;
        this.authenticationManager = authenticationManager;
        this.refrenceService = refrenceService;
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("model", new UserVM());
        return "page-login";
    }

    @ResponseBody
    @GetMapping("/user")
    public List<User> userIndex() {
        return service.getAll();
    }

    @ResponseBody
    @GetMapping("/user/id/{id}")
    public User userGetId(@PathVariable String id) {
        User u = service.getbyId(id).get();
        return u;
    }

    @ResponseBody
    @GetMapping("user/delete/{id}")
    public String userDelete(@PathVariable String id) {
        return service.delete(id);
    }
    
    @PostMapping("/login")
    public String createAuthentication(@RequestBody UserVM userVM) throws Exception {
        System.out.println(userVM);
        try {
            authenticationManager.authenticate((new UsernamePasswordAuthenticationToken(userVM.getUsername(), userVM.getPassword())));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        UserRefrence userDetails = refrenceService.loadUserByUsername(userVM.getUsername());
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.getAuthorities());
        return "redirect:/admin";
    }

}
